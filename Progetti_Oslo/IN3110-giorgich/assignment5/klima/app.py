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
from klima import (
    MONTHS,
    MONTH_TEMPERATURE,
    getData,
    plotTemps,
)

app = FastAPI()
templates = Jinja2Templates(directory="templates")

@app.get("/")
def mainPage(request : Request):
    return templates.TemplateResponse(
        "klima.html",
        {
            "request": request,
        }
    )

@app.get("/plot_temp.json")
def show_Temp():
    df = getData()
    chart = plotTemps(df)
    return chart.to_dict()

def main():
    """Launches the application on port 5000 with uvicorn"""
    # use uvicorn to launch your application on port 5000
    uvicorn.run(app, host="127.0.0.1", port=5000)


if __name__ == "__main__":
    main()
