#!/usr/bin/python3
import pandas as pd

dataframe = pd.read_csv('./output/stats.csv')
print(dataframe.describe())
print(dataframe)

