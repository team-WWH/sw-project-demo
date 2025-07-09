import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from statsmodels.tsa.arima.model import ARIMA
from statsmodels.tsa.statespace.dynamic_factor import DynamicFactor
from sklearn.preprocessing import StandardScaler

print("\nwjjjr冲突行1")
print("\nwjjjr冲突行2")
print("\nwjjjr冲突行3")
print("\nwjjjr冲突行4")
print("\n冲突行5")
print("\n冲突行6")
print("\n冲突行7")
print("\n冲突行8")

plt.rcParams['font.sans-serif'] = ['SimHei']
plt.rcParams['axes.unicode_minus'] = False

# ======================
# 1. 加载实际数据
# ======================
try:
    df = pd.read_excel(
        'D:/PythonProject4/industry_yearly.xlsx',
        parse_dates=['year'],
        date_parser=lambda x: pd.to_datetime(x, format='%Y/%m/%d')
    )
    print("\n成功加载数据，数据概况：")
    print(f"- 时间范围：{df['year'].min().year} 至 {df['year'].max().year}")
    print(f"- 数据维度：{df.shape[0]} 年 × {df.shape[1] - 1} 变量")
except Exception as e:
    raise SystemExit(f"数据加载失败：{str(e)}")

# 数据预处理
try:
    df = df.set_index('year').sort_index()
    df = df.asfreq('YE')
    df.columns = ['profit', 'export', 'patent_ratio', 'fortune500_ratio']
    print("\n前5行数据：")
    print(df.head())
except Exception as e:
    raise SystemExit(f"数据预处理失败：{str(e)}")

# 计算相关系数矩阵
correlation_matrix = df.corr()
print("\n=== 变量相关系数矩阵 ===")
print(correlation_matrix)

# 标准化处理
scaler = StandardScaler()
df_scaled = pd.DataFrame(
    scaler.fit_transform(df),
    index=df.index,
    columns=df.columns
)

# ======================
# 3. 动态因子模型构建
# ======================
try:
    model = DynamicFactor(
        endog=df_scaled,
        k_factors=1,
        factor_order=1,
        error_cov_type='diagonal',
        error_order=0
    )

    results = model.fit(
        method='powell',
        maxiter=1000,
        disp=True
    )
    print("\n模型拟合成功！")
    print(results.summary())
except Exception as e:
    raise SystemExit(f"模型拟合失败：{str(e)}")

# ======================
# 4. 模型结果解析
# ======================
n_vars = df.shape[1]
loadings = results.params[:n_vars].values.reshape(-1, 1)

print("\n=== 因子载荷矩阵 ===")
loadings_df = pd.DataFrame(
    loadings,
    index=df.columns,
    columns=['Factor1']
)
print(loadings_df.T)
print("说明：正值表示变量与行业趋势正相关，绝对值越大影响越强")

# 提取趋势因子
factor = pd.Series(
    results.factors.smoothed[0],
    index=df.index
)

# ======================
# 5. 预测设置
# ======================
forecast_steps = 5
last_year = df.index[-1].year
forecast_years = pd.date_range(
    start=f'{last_year + 1}-12-31',
    periods=forecast_steps,
    freq='YE'
)

# ======================
# 6. ARIMA模型预测趋势因子
# ======================
try:
    # 拟合ARIMA模型
    arima_model = ARIMA(factor, order=(1, 1, 1))
    # from pmdarima import auto_arima
    #
    # arima_model = auto_arima(
    #     factor,
    #     seasonal=False,
    #     stepwise=True,
    #     trace=True
    # )
    arima_results = arima_model.fit()
    print("\nARIMA模型拟合成功！")
    print(arima_results.summary())

    # 预测趋势因子
    factor_forecast = arima_results.forecast(steps=forecast_steps)
    factor_forecast_ci = arima_results.get_forecast(steps=forecast_steps).conf_int()

    # 计算ARIMA模型的拟合值
    factor_fitted = arima_results.fittedvalues

except Exception as e:
    raise SystemExit(f"ARIMA预测失败：{str(e)}")

# ======================
# 7. 动态因子模型预测
# ======================
try:
    # 获取预测
    forecast = results.get_forecast(steps=forecast_steps)
    forecast_mean = forecast.predicted_mean

    # 反向标准化预测值
    forecast_mean_orig = scaler.inverse_transform(forecast_mean)
    df_forecast = pd.DataFrame(
        forecast_mean_orig,
        index=forecast_years,
        columns=df.columns
    )

    # 获取置信区间
    forecast_ci = forecast.conf_int()
    # 分别处理每个变量的置信区间
    ci_lower = np.zeros((forecast_steps, n_vars))
    ci_upper = np.zeros((forecast_steps, n_vars))

    for i in range(n_vars):
        # 获取该变量的置信区间
        var_ci = forecast_ci.iloc[:, [i * 2, i * 2 + 1]].values

        # 创建临时数组用于反向标准化
        temp_lower = np.zeros((forecast_steps, n_vars))
        temp_upper = np.zeros((forecast_steps, n_vars))

        # 只对当前变量进行反向标准化
        temp_lower[:, i] = var_ci[:, 0]
        temp_upper[:, i] = var_ci[:, 1]

        # 反向标准化
        ci_lower[:, i] = scaler.inverse_transform(temp_lower)[:, i]
        ci_upper[:, i] = scaler.inverse_transform(temp_upper)[:, i]

except Exception as e:
    raise SystemExit(f"预测失败：{str(e)}")

# ======================
# 8. 可视化呈现
# ======================
plt.figure(figsize=(14, 15))

# (1) ARIMA模型诊断图
plt.subplot(5, 1, 1)
plt.plot(factor.index, factor, 'b-', label='实际值')
plt.plot(factor.index, factor_fitted, 'r--', label='拟合值')
plt.plot(forecast_years, factor_forecast, 'g--', marker='o', label='预测值')
plt.fill_between(forecast_years, factor_forecast_ci.iloc[:, 0], factor_forecast_ci.iloc[:, 1],
                 color='green', alpha=0.1)
plt.title('ARIMA模型拟合与预测', fontsize=14)
plt.grid(alpha=0.3)
plt.ylabel('标准化值')
plt.legend()

# (2) ARIMA残差图
plt.subplot(5, 1, 2)
residuals = factor - factor_fitted
plt.plot(residuals.index, residuals, 'b-')
plt.axhline(y=0, color='r', linestyle='--')
plt.title('ARIMA模型残差', fontsize=14)
plt.grid(alpha=0.3)
plt.ylabel('残差值')

# (3) 相关系数热力图
plt.subplot(5, 1, 3)
plt.imshow(correlation_matrix, cmap='coolwarm', interpolation='nearest')
plt.colorbar()
plt.xticks(range(len(df.columns)), df.columns, rotation=45)
plt.yticks(range(len(df.columns)), df.columns)
plt.title('变量相关系数热力图', fontsize=14)

# (4) 历史数据趋势
plt.subplot(5, 1, 4)
colors = ['#d7191c', '#fdae61', '#abdda4', '#2b83ba']
for i, col in enumerate(df.columns):
    plt.plot(df[col], label=col.replace('_', ' ').title(),
             color=colors[i], lw=2, alpha=0.8)
plt.title('历史数据趋势（2003-2023）', fontsize=14)
plt.legend(bbox_to_anchor=(1.02, 1), loc='upper left')
plt.grid(alpha=0.3)

# (5) 预测结果
plt.subplot(5, 1, 5)
for i, col in enumerate(df.columns):
    plt.plot(df.index.year, df[col], color=colors[i], alpha=0.3)
    plt.plot(forecast_years.year, df_forecast[col],
             color=colors[i], linestyle='--', marker='o')
    plt.fill_between(forecast_years.year, ci_lower[:, i], ci_upper[:, i],
                     color=colors[i], alpha=0.1)

plt.title('5年预测结果（2024-2028）', fontsize=14)
plt.grid(alpha=0.3)
plt.xlabel('年份')

plt.tight_layout()
plt.savefig('行业趋势分析.png', dpi=300, bbox_inches='tight')
plt.show()

# ======================
# 9. 结果输出
# ======================
print("\n=== 未来5年预测值 ===")
print(df_forecast.applymap(lambda x: f"{x:.2f}"))

# 保存预测结果
df_combined = pd.concat([df, df_forecast])
df_combined.to_excel("行业趋势分析与预测.xlsx")

print("\n分析完成！结果已保存至：行业趋势分析.png 和 行业趋势分析与预测.xlsx")