#!/usr/bin/env python3
"""
Fetch data from https://www.hvakosterstrommen.no/strompris-api
and visualize it using various methods.
"""

import datetime

import altair as alt
import pandas as pd
import requests
import requests_cache

# install an HTTP request cache
# to avoid unnecessary repeat requests for the same data
# this will create the file http_cache.sqlite
requests_cache.install_cache()


# task 5.1:


def fetch_day_prices(date: datetime.date = None, location: str = "NO1") -> pd.DataFrame:
    """Fetch one day of data for one location from hvakosterstrommen.no API

    Args:
        date (datetime.date): The date to be requested to the API.
        location (str): The location string for the desired area.

    Returns:
        pd.DataFrame:  A panda Dataframe containg two colums:
                        NOK_per_kWh (float): the cost of the gas at a specific date and time
                        time_start (datetime.date): the day of the associated data 
    """
    if date is None:
        date = datetime.date.today()

    oldest_date = datetime.date(2023,10,1) # Get the oldest date
    if date >= oldest_date: # check if the current date is greated then the older date
        url = f"https://www.hvakosterstrommen.no/api/v1/prices/{date.year}/{date.month}-{date.strftime('%d')}_{location}.json"
        res = requests.get(url)
        Nok = []
        times = []
        for time in res.json(): # for each hour in the response save the Nok and the date
            Nok.append(time["NOK_per_kWh"])
            times.append(time["time_start"])
        dict = {"NOK_per_kWh": Nok, "time_start": times}
        df = pd.DataFrame.from_dict(dict) # create the dataframe
        df.astype({"NOK_per_kWh":"float"}) # convert the prices into floats
        df["time_start"] = pd.to_datetime(df["time_start"], utc=True).dt.tz_convert("Europe/Oslo") # convert dates to datetime and account for daylight saving
        return df
    else:
        return None
    


# LOCATION_CODES maps codes ("NO1") to names ("Oslo")
LOCATION_CODES = {
    "NO1" : "Oslo / Øst-Norge",
    "NO2" : "Kristiansand / Sør-Norge",
    "NO3" : "Trondheim / Midt-Norge",
    "NO4" : "Tromsø / Nord-Norge",
    "NO5" : "Bergen / Vest-Norge",
}

# task 1:


def fetch_prices(
    end_date: datetime.date = None,
    days: int = 7,
    locations: list[str] = tuple(LOCATION_CODES.keys()),
) -> pd.DataFrame:
    """Fetch prices for multiple days and locations into a single DataFrame

    Args:
        end_date (datetime.date): The last date that we want to be requested
        days (int): The number of days from the end_date we want to request
        location (str): The location string for the desired area.

    Returns:
        pd.DataFrame:  A panda Dataframe containg four colums:
                        NOK_per_kWh (float): the cost of the gas at a specific date and time
                        time_start (datetime.date): the day of the associated data
                        location_code (string): Code for the location
                        location (string): full name for the location
    """
    if end_date is None:
        end_date = datetime.date.today()
    if locations is None:
        locations = LOCATION_CODES.keys()
    if days is None:
        days = 7
    df = pd.DataFrame()
    for day in range(days): 
        current_date = end_date - datetime.timedelta(days=day) # for each day calculate the date
        for code in locations:
            ret_dataframe = fetch_day_prices(current_date, code) # get the date
            if ret_dataframe is not None:
                ret_dataframe["location_code"] = code
                ret_dataframe["location"] = LOCATION_CODES[code]
                df = pd.concat([df, ret_dataframe], ignore_index=True) # and concatenate the current dataframe with the total df
    return df


# task 5.1:


def plot_prices(df: pd.DataFrame) -> alt.Chart:
    """Plot energy prices over time

    x-axis is time_start
    y-axis is price in NOK
    each location has its own line

    Args:
        df (pd.DataFrame): A panda Dataframe containg four colums:
                            NOK_per_kWh (float): the cost of the gas at a specific date and time
                            time_start (datetime.date): the day of the associated data
                            location_code (string): Code for the location
                            location (string): full name for the location

    Returns:
        alt.chart: The Chart object created, which describes the plot of the given data
    """
    chart = alt.Chart(df).mark_line().encode( # plot the data
        x='time_start:T',
        y='NOK_per_kWh:Q',
        color='location:N',
        tooltip=[
            "NOK_per_kWh",
            "time_start",
            "location_code",
            "location",
        ],
    ).properties(
            width=1000,
            height=600
        )
    return chart


# Task 5.4


def plot_daily_prices(df: pd.DataFrame) -> alt.Chart:
    """Plot the daily average price

    x-axis should be time_start (day resolution)
    y-axis should be price in NOK

    You may use any mark.

    Make sure to document arguments and return value...
    """
    raise(NotImplementedError)


# Task 5.6

ACTIVITIES = {
    # activity name: energy cost in kW
    "shower": 30,
    "baking": 2.5,
    "heat": 1,
}


def plot_activity_prices(
    df: pd.DataFrame, activity: str = "shower", minutes: float = 10
) -> alt.Chart:
    """
    Plot price for one activity by name,
    given a data frame of prices, and its duration in minutes.

    Args:
        df (pd.DataFrame): A panda Dataframe containg four colums:
                            NOK_per_kWh (float): the cost of the gas at a specific date and time
                            time_start (datetime.date): the day of the associated data
                            location_code (string): Code for the location
                            location (string): full name for the location
        activity (str): The name of the activity
        minutes (float): The duration of the activity in minutes

    Returns:
        alt.chart: The Chart object created, which describes the plot of the given data
    """
    if df is not None:
        activity_cost = ACTIVITIES[activity]
        if minutes <= 60: # check if the duration is less then 60 mins
            df["NOK_per_kWh"] = df["NOK_per_kWh"] * activity_cost * minutes # calculate the cost
            df = df.rename(columns={"NOK_per_kWh": "activity_price"})
        df["activity"] = activity

        chart = alt.Chart(df).mark_line().encode( # plot the data
            x='time_start:T',
            y='activity_price:Q',
            color='activity:N',
            tooltip=[
                "activity_price",
                "time_start",
                "location_code",
                "location",
                "activity",
            ],
        ).properties(
            width=1000,
            height=600
        )
        return chart




def main():
    """Allow running this module as a script for testing."""
    #df_2 = fetch_day_prices(datetime.date(2022,11,12), "NO2")
    df = fetch_prices(locations={"NO2"})
    chart = plot_activity_prices(df)
    #chart = plot_prices(df)
    # showing the chart without requiring jupyter notebook or vs code for example
    # requires altair viewer: `pip install altair_viewer`
    alt.renderers.enable("mimetype")
    chart.show()


if __name__ == "__main__":
    main()
