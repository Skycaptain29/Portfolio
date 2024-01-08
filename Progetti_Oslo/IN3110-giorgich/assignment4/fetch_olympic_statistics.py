"""
Task 4

collecting olympic statistics from wikipedia
"""

from __future__ import annotations

from pathlib import Path
from requesting_urls import get_html
from bs4 import BeautifulSoup
import re
import matplotlib.pyplot as plt
import pandas as pd

# Countries to submit statistics for
scandinavian_countries = ["Norway", "Sweden", "Denmark"]

# Summer sports to submit statistics for
summer_sports = ["Sailing", "Athletics", "Handball", "Football", "Cycling", "Archery"]


def report_scandi_stats(url: str, sports_list: list[str], work_dir: str | Path) -> None:
    """
    Given the url, extract and display following statistics for the Scandinavian countries:

      -  Total number of gold medals for for summer and winter Olympics
      -  Total number of gold, silver and bronze medals in the selected summer sports from sport_list
      -  The best country in number of gold medals in each of the selected summer sports from sport_list

    Display the first two as bar charts, and the last as an md. table and save in a separate directory.

    Parameters:
        url (str) : url to the 'All-time Olympic Games medal table' wiki page
        sports_list (list[str]) : list of summer Olympic games sports to display statistics for
        work_dir (str | Path) : (absolute) path to your current working directory

    Returns:
        None
    """

    # Make a call to get_scandi_stats
    # Plot the summer/winter gold medal stats
    # Iterate through each sport and make a call to get_sport_stats
    # Plot the sport specific stats
    # Make a call to find_best_country_in_sport for each sport
    # Create and save the md table of best in each sport stats

    work_dir = Path(work_dir)
    country_dict = get_scandi_stats(url)

    stats_dir = work_dir / "olympic_games_results"
    stats_dir.mkdir(exist_ok=True)
    plot_scandi_stats(country_dict, stats_dir)

    best = []
    for sport in sports_list:
        results: dict[str, dict[str, int]] = {}
        results["Sweden"] = get_sport_stats(country_dict["Sweden"]["url"], sport)
        results["Norway"] = get_sport_stats(country_dict["Norway"]["url"], sport)
        results["Denmark"] = get_sport_stats(country_dict["Denmark"]["url"], sport)
        plot_sport_stats(results, sport, stats_dir)
        best.append((sport,find_best_country_in_sport(results,"Gold")))
    
    headers = ["Sport", "Best Country"]
    df = pd.DataFrame(best, columns=headers)
    df.to_markdown(stats_dir/"best_of_sport_by_Gold.md", index=False)



def get_scandi_stats(
    url: str,
) -> dict[str, dict[str, str | dict[str, int]]]:
    """Given the url, extract the urls for the Scandinavian countries,
       as well as number of gold medals acquired in summer and winter Olympic games
       from 'List of NOCs with medals' table.

    Parameters:
      url (str): url to the 'All-time Olympic Games medal table' wiki page

    Returns:
      country_dict: dictionary of the form:
        {
            "country": {
                "url": "https://...",
                "medals": {
                    "Summer": 0,
                    "Winter": 0,
                },
            },
        }

        with the tree keys "Norway", "Denmark", "Sweden".
    """

    html = get_html(url)
    soup = BeautifulSoup(html, "html.parser")
    pattern_legend = re.compile(r"^ Special delegation, not an actual nation", re.IGNORECASE)
    headline = soup.find(class_="legend")
    table = headline.find_next("table")
    base_url = "https://en.wikipedia.org"

    rows = table.find_all("tr")

    country_dict: dict[str, dict[str, str | dict[str, int]]] = {}

    for row in rows:
        cols = row.find_all("td")
        if cols and cols[0].a.get_text() in scandinavian_countries:
            country_dict[cols[0].a.get_text(strip=True)] = {"url":base_url+cols[0].a.get("href"), "medals":{"Summer":int(cols[2].get_text(strip=True)), "Winter":int(cols[7].get_text(strip=True))}}
            
    return country_dict


def get_sport_stats(country_url: str, sport: str) -> dict[str, int]:
    """Given the url to country specific performance page, get the number of gold, silver, and bronze medals
      the given country has acquired in the requested sport in summer Olympic games.

    Parameters:
        - country_url (str) : url to the country specific Olympic performance wiki page
        - sport (str) : name of the summer Olympic sport in interest. Should be used to filter rows in the table.

    Returns:
        - medals (dict[str, int]) : dictionary of number of medal acquired in the given sport by the country
                          Format:
                          {"Gold" : x, "Silver" : y, "Bronze" : z}
    """
    html = get_html(country_url)
    soup = BeautifulSoup(html, "html.parser")
    
    pattern_table = re.compile(r"^Medals by summer sport", re.IGNORECASE)
    pattern_sport = re.compile(fr"{sport}", re.IGNORECASE)

    headline = soup.find(class_="mw-headline", string=pattern_table)
    table = headline.find_next("table")

    medals = {
        "Gold": 0,
        "Silver": 0,
        "Bronze": 0,
    }

    rows = table.find_all("tr")

    for row in rows:
        cols = row.find_all("td")
        if row.a and pattern_sport.search(row.a.get_text()):
            medals["Gold"] = int(cols[0].get_text())
            medals["Silver"] = int(cols[1].get_text())
            medals["Bronze"] = int(cols[2].get_text())
            
    return medals


def find_best_country_in_sport(
    results: dict[str, dict[str, int]], medal: str = "Gold"
) -> str:
    """Given a dictionary with medal stats in a given sport for the Scandinavian countries, return the country
        that has received the most of the given `medal`.

    Parameters:
        - results (dict) : a dictionary of country specific medal results in a given sport. The format is:
                        {"Norway" : {"Gold" : 1, "Silver" : 2, "Bronze" : 3},
                         "Sweden" : {"Gold" : 1, ....},
                         "Denmark" : ...
                        }
        - medal (str) : medal type to compare for. Valid parameters: ["Gold" | "Silver" |"Bronze"]. Should be used as a key
                          to the medal dictionary.
    Returns:
        - best (str) : name of the country(ies) leading in number of gold medals in the given sport
                       If one country leads only, return its name, like for instance 'Norway'
                       If two countries lead return their names separated with '/' like 'Norway/Sweden'
                       If all or none of the countries lead, return string 'None'
    """
    valid_medals = {"Gold", "Silver", "Bronze"}
    if medal not in valid_medals:
        raise ValueError(
            f"{medal} is invalid parameter for ranking, must be in {valid_medals}"
        )

    # Get the requested medals and determine the best
    if results["Norway"][medal] == results["Sweden"][medal] == results["Denmark"][medal]:
        best = "None"
    elif results["Norway"][medal] > results["Sweden"][medal] and results["Norway"][medal] > results["Denmark"][medal]:
        best = "Norway"
    elif results["Sweden"][medal] > results["Norway"][medal] and results["Sweden"][medal] > results["Denmark"][medal]:
        best = "Sweden"
    elif results["Denmark"][medal] > results["Sweden"][medal] and results["Denmark"][medal] > results["Norway"][medal]:
        best = "Denmark"
    elif results["Norway"][medal] == results["Sweden"][medal]:
        best = "Norway/Sweden"
    elif results["Norway"][medal] == results["Denmark"][medal]:
        best = "Norway/Denmark"
    elif results["Sweden"][medal] == results["Denmark"][medal]:
        best = "Sweden/Denmark"
    return best


# Define your own plotting functions and optional helper functions


def plot_scandi_stats(
    country_dict: dict[str, dict[str, str | dict[str, int]]],
    output_parent: str | Path | None = None,
) -> None:
    """Plot the number of gold medals in summer and winter games for each of the scandi countries as bars.

    Parameters:
      country_dict (dict[str, dict[str, int]]) : a nested dictionary of country names and the corresponding number of summer and winter
                            gold medals from 'List of NOCs with medals' table.
                            Format:
                            {"country_name": {"Summer" : x, "Winter" : y}}
      output_parent (str | Path) : parent file path to save the plot in
    Returns:
      None
    """
    all_names = []
    medals_summer = []
    medals_winter = []
    for country, stats in country_dict.items():
        medals_summer.append(stats["medals"]["Summer"])
        medals_winter.append(stats["medals"]["Winter"])
        
        all_names.append(country)
        
    x_summer = range(0,12,4)
    x_winter = range(1,12,4)
    bars = plt.bar(x_summer, medals_summer, label="Summer")
    plt.bar_label(bars)
    bars = plt.bar(x_winter, medals_winter , label="Winter")
    plt.bar_label(bars)
    plt.xticks(range(0,12,4), all_names, rotation=15)
    plt.legend(loc=0)
    plt.grid(False)
    plt.title("Total of gold medals for Summer and Winter Sports")
    plt.ylabel("Number of golden medals")

    file_path = output_parent/"total_medal_ranking.png"
    plt.savefig(file_path)
    plt.clf()

def plot_sport_stats(
    results: dict[str, dict[str, int]],
    sport: str,
    output_parent: str | Path | None = None,
    ) -> None:
    """
    Plot the number of medals in a specific sport for each of the scandi countries as bars.
    Parameters:
      results (dict[str, dict[str, int]]) : a nested dictionary of country names and the corresponding number of medals for each sport
                            from the table found in the {Country} at the Olympics.
                            Format:
                            {"country_name": {"Gold" : x, "Silver" : y, "Bronze" : z}}
      output_parent (str | Path) : parent file path to save the plot in
      sport (str) : Name of the sport to be plotted
      
    Returns:
      None"""
    names = []
    gold_medals = []
    silver_medals = []
    bronze_medals = []
    for country in results:
        names.append(country)
        gold_medals.append(results[country]["Gold"])
        silver_medals.append(results[country]["Silver"])
        bronze_medals.append(results[country]["Bronze"])
    
    x_gold = range(0,15,5)
    x_silver = range(1,15,5)
    x_bronze = range(2,15,5)
    bars = plt.bar(x_gold, gold_medals, label="Gold")
    plt.bar_label(bars)
    bars = plt.bar(x_silver, silver_medals, label="Silver")
    plt.bar_label(bars)
    bars = plt.bar(x_bronze, bronze_medals, label="Bronze")
    plt.bar_label(bars)
    plt.xticks(range(0,15,5), names, rotation=15)
    plt.legend(loc=0)
    plt.grid(False)
    plt.title(f"Total of  medals for {sport}")
    plt.ylabel("Number of medals")
    file_path = output_parent/f"{sport}_medal_ranking.png"
    plt.savefig(file_path)
    plt.clf()

        


# run the whole thing if called as a script, for quick testing
if __name__ == "__main__":
    url = "https://en.wikipedia.org/wiki/All-time_Olympic_Games_medal_table"
    work_dir = "."
    report_scandi_stats(url, summer_sports, work_dir)
