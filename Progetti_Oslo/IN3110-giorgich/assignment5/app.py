"""
strompris fastapi app entrypoint
"""
import datetime
import os
from typing import List, Optional

import altair as alt
from fastapi import FastAPI, Query, Request
from fastapi.templating import Jinja2Templates
from starlette.staticfiles import StaticFiles
import uvicorn
from pathlib import Path
from strompris import (
    ACTIVITIES,
    LOCATION_CODES,
    fetch_day_prices,
    fetch_prices,
    plot_activity_prices,
    plot_daily_prices,
    plot_prices,
)

app = FastAPI()
templates = Jinja2Templates(directory="templates")
docs_templates = Jinja2Templates(directory="docs/_build/html")

app.mount(
    "/_static",
    StaticFiles(directory=Path(__file__).parent.absolute()/ "docs" / "_build" / "html" / "_static"),
    name="_static",
)


# `GET /` should render the `strompris.html` template
# with inputs:
# - request
# - location_codes: location code dict
# - today: current date
# - n_days: default number of days (7)

@app.get("/")
def mainPage(request : Request):
    today = datetime.date.today()
    n_days = 7
    return templates.TemplateResponse(
        "strompris.html", # pass the main html page
        {
            "request": request,
            "location_codes": LOCATION_CODES, # pass default locations
            "today": today, # pass default date
            "n_days": n_days, # pass default days number
        }
    )




# GET /plot_prices.json should take inputs:
# - locations (list from Query)
# - end (date)
# - days (int, default=7)
# all inputs should be optional
# return should be a vega-lite JSON chart (alt.Chart.to_dict())
# produced by `plot_prices`
# (task 5.6: return chart stacked with plot_daily_prices)

@app.get("/plot_prices.json")
def activityPage(request: Request, end: str = ("{:%Y-%m-%d}".format(datetime.datetime.now())), days: int = 7, locations: Optional[List[str]] = Query(default=None)):
    print(end)
    if locations is None:
        locations = LOCATION_CODES
    if end  is not None:
        end = datetime.datetime.strptime( end ,'%Y-%m-%d').date()
    else:
        end = datetime.date.today()
    
    df = fetch_prices(end, days, locations) # get the dataframe
    chart = plot_prices(df) # plot the dataframe
    return chart.to_dict() # return it

# Task 5.6 (bonus):
# `GET /activity` should render the `activity.html` template
# activity.html template must be adapted from `strompris.html`
# with inputs:
# - request
# - location_codes: location code dict
# - activities: activity energy dict
# - today: current date

@app.get("/activity")
def activityPage(request: Request):
    return templates.TemplateResponse(
        "activity.html",
        {
            "request": request,
            "location_codes": LOCATION_CODES, # pass default locations
            "today": datetime.date.today(), # pass default date 
            "activities": ACTIVITIES, # pass default activities
        }
    )


# Task 5.6:
# `GET /plot_activity.json` should return vega-lite chart JSON (alt.Chart.to_dict())
# from `plot_activity_prices`
# with inputs:
# - location (single, default=NO1)
# - activity (str, default=shower)
# - minutes (int, default=10)

@app.get("/plot_activity.json")
def returnChartActivity(request: Request, activity: str = "shower", minutes: int = 10, location: str = "NO1"):
    today = datetime.date.today()
    days = 7
    df = fetch_prices(today, days, [location]) # get the dataframe
    chart = plot_activity_prices(df, activity, minutes) # plot the dataframe
    return chart.to_dict() # return it
 

# mount your docs directory as static files at `/help`

@app.get("/help")
def displayHelp(request: Request,):
    return docs_templates.TemplateResponse(
        "api.html", # return the help page
        {
            "request": request,
        }
    )


def main():
    """Launches the application on port 5000 with uvicorn"""
    # use uvicorn to launch your application on port 5000
    uvicorn.run(app, host="127.0.0.1", port=5000)


if __name__ == "__main__":
    main()
