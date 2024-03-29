import pytest
from collect_dates import find_dates
from requesting_urls import get_html

date_text = """
DMY: 2 January 2020
MDY: February 12, 1954
YMD: 2015 March 31
ISO: 2022-04-15
"""


@pytest.mark.task2
@pytest.mark.parametrize(
    "date_str, date",
    [
        ("DMY: 2 January 2020", "2020/01/02"),
        ("MDY: February 12, 1954", "1954/02/12"),
        ("YMD: 2015 March 31", "2015/03/31"),
        ("ISO: 2022-04-15", "2022/04/15"),
        ("DMY: 22 june 2020", "2020/06/22"),
        ("MDY: October 13, 2025", "2025/10/13"),
        ("YMD: 2019 December 2", "2019/12/02"),
    ],
)
def test_find_dates(date_str, date):
    dates = find_dates(date_str)
    assert isinstance(dates, list)
    assert all(isinstance(d, str) for d in dates)
    assert dates == [date]


@pytest.mark.task2
@pytest.mark.parametrize(
    "date_str, expected",
    [
        (
            """
            DMY: 2 January 2020
            MDY: February 12, 1954
            YMD: 2015 March 31
            ISO: 2022-04-15
            DMY: 22 june 2020
            MDY: October 13, 2025
            YMD: 2020 january 2
            """,
            [
                "2020/01/02",
                "1954/02/12",
                "2015/03/31",
                "2022/04/15",
                "2020/06/22",
                "2025/10/13",
                "2020/01/02",
            ],
        )
    ],
)
def test_find_dates_order(date_str, expected):
    dates = find_dates(date_str)
    assert isinstance(dates, list)
    assert all(isinstance(d, str) for d in dates)
    assert dates == expected


@pytest.mark.task2
@pytest.mark.parametrize(
    "url, expected",
    [
        (
            "https://en.wikipedia.org/wiki/Serena_Williams",
            ["1981/09/26", "2016/07/24", "2016/09/05"],
        ),
        (
            "https://en.wikipedia.org/wiki/Marie_Curie",
            ["1867/11/07", "1934/07/04", "1898/04/14"],
        ),
        (
            "https://en.wikipedia.org/wiki/Hans_Petter_Langtangen",
            ["1962/01/03", "2016/02/05"],
        ),
    ],
)
def test_find_urls(url, expected):
    html = get_html(url)
    dates = find_dates(html)
    for expected_date in expected:
        assert expected_date in dates


@pytest.mark.task2
@pytest.mark.parametrize(
    "date_str, expected",
    [
        (
            "2020-10-01, October 02, 2020, 2020 October 03 lala 04 October 2020",
            ["2020/10/01", "2020/10/02", "2020/10/03", "2020/10/04"],
        )
    ],
)
def test_date_order(date_str, expected):
    """
    Checks that the dates are sorted after appearance in the document, like the assignment asks for.
    """
    dates = find_dates(date_str)
    assert dates == expected, "Order wrong in finding dates"
