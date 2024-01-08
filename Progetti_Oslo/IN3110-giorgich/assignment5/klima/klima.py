import datetime

import altair as alt
import pandas as pd
import altair_viewer
import re

MONTH_TEMPERATURE = {
    1: 12.0,
    2: 12.1,
    3: 12.7,
    4: 13.7,
    5: 14.8,
    6: 15.5,
    7: 15.8,
    8: 15.6,
    9: 15.0,
    10: 14.0,
    11: 12.9,
    12: 12.2,
}

MONTHS = {
    1: "jan",
    2: "feb",
    3: "mar",
    4: "apr",
    5: "may",
    6: "jun",
    7: "jul",
    8: "aug",
    9: "sep",
    10: "oct",
    11: "nov",
    12: "dec",
}

def getData() -> pd.DataFrame:
    """
    Fetch one day of data for one location from the data.csv file

    Args:
        None

    Returns:
        pd.DataFrame:  A panda Dataframe containg three colums:
                        Temp (float): The temperature of a specific month and year
                        Month (str): The month of the temperature
                        Year (int): The year of the temperature
    """
    df = pd.read_csv("data.csv", index_col=False) # Read the csv
    pattern = re.compile("\d{4}", re.IGNORECASE) # Pattern to extract the year
    
    temp = []
    month = []
    year = []
    
    for i in range(len(df)):
        temp.append(df.iloc[i,1] + MONTH_TEMPERATURE[df.iloc[i,0]%100]) # Calculate the anomaly + monthly temperature
        month.append(str(MONTHS[df.iloc[i,0]%100])) # Extract the month
        year.append(int(pattern.search(str(df.iloc[i,0])).group())) #Extract the year
    dict = {"Temp": temp, "Month": month, "Year": year}
    newdf = pd.DataFrame().from_dict(dict) # create the dataframe
    newdf.astype({"Month":"str"})
    newdf.astype({"Year":"int"})
    return newdf

def plotTemps(df: pd.DataFrame) -> alt.Chart:
    """
    Plots the temperatures given by a dataframe

    Args:
        pd.DataFrame:  A panda Dataframe containg three colums:
                        Temp (float): The temperature of a specific month and year
                        Month (str): The month of the temperature
                        Year (int): The year of the temperature

    Returns:
        alt.chart: The Chart object created, which describes the plot of the given data
    """
    months = ["jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dev"]

    chart = alt.Chart(df).mark_line().encode( # Plot the data for every year
        x=alt.X('Month:N', sort=months),
        y=alt.Y('Temp:Q', scale=alt.Scale(domain=[11, 18])),
        color=alt.Color('Year:N', scale=alt.Scale(scheme='purples'), legend=None),
        detail="Year:N",
         tooltip=alt.Tooltip([
            'Year:O', 
            'Temp:Q',
            ]),
        
    ).properties(
        width=1000,
        height=600
    )

    current_year = alt.Chart(df[df['Year'] == 2023]).mark_line(strokeWidth=4, color="purple").encode( # Plot the data for year 2023 in bold
        x=alt.X('Month:N', sort=months),
        y='Temp:Q',
        tooltip=alt.Tooltip([
            'Year:O', 
            'Temp:Q',
            ]),
    ).properties(
        width=1000,
        height=600
    )

    chart = alt.layer(chart, current_year) # Layer the charts on top of each other
    return chart

def main():
    df = getData()
    chart = plotTemps(df)
    #chart.show()

if __name__ == "__main__":
    main()