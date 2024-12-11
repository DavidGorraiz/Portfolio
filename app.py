import os

from cs50 import SQL
from flask import Flask, redirect, render_template, request, session
from flask_session import Session
from werkzeug.security import check_password_hash, generate_password_hash
from datetime import date

from helpers import apology, login_required, usd

# Configure application
app = Flask(__name__)

# Custom filter
app.jinja_env.filters["usd"] = usd

# Configure session to use filesystem (instead of signed cookies)
app.config["SESSION_PERMANENT"] = False
app.config["SESSION_TYPE"] = "filesystem"
Session(app)

# Configure CS50 Library to use SQLite database
db = SQL("sqlite:///finance.db")

# Variable to know the date
today = date.today()


@app.after_request
def after_request(response):
    """Ensure responses aren't cached"""
    response.headers["Cache-Control"] = "no-cache, no-store, must-revalidate"
    response.headers["Expires"] = 0
    response.headers["Pragma"] = "no-cache"
    return response


@app.route("/")
def index():
    """Show home page"""
    return render_template("index.html")

@app.route("/dashboard", methods=["GET", "POST"])
@login_required
def dashboard():
    # User reached route via POST (as by submitting a form via POST)
    if request.method == "POST":
        # Get the list the values from the form revenue
        id_revenue = request.form.get('id-revenue')
        if id_revenue != None:
            db.execute(
                "DELETE FROM revenues WHERE id_user = ? AND id_revenue = ?",
                session["user_id"], id_revenue
            )
        # Get the list the values from the form outgoing
        id_outgoing = request.form.get('id-outgoing')
        if id_outgoing != None:
            db.execute(
                "DELETE FROM outgoings WHERE id_user = ? AND id_outgoing = ?",
                session["user_id"], id_outgoing
            )

        # Get the list the values from the form savings
        id_saving = request.form.get('id-saving')
        if id_saving != None:
            db.execute(
                "DELETE FROM savings WHERE id_user = ? AND id_saving = ?",
                session["user_id"], id_saving
            )

    # Query database for username
    rows = db.execute(
        "SELECT u.username, c.name_country, c.currency FROM users AS u JOIN countries AS c ON u.id_country = c.id_country WHERE id_user = ?", session["user_id"]
    )

    username = rows[0]["username"]
    country = rows[0]["name_country"]
    currency = rows[0]["currency"]

    # get the table revenues
    revenues = db.execute(
        "SELECT * FROM revenues WHERE id_user = ?", session["user_id"]
    )
    # get the table outgoings
    outgoings = db.execute(
        "SELECT * FROM outgoings WHERE id_user = ?", session["user_id"]
    )
    # get the table savings
    savings = db.execute(
        "SELECT * FROM savings WHERE id_user = ?", session["user_id"]
    )


    # Delete the period
    # Get the date_start and date_end
    date_start = request.form.get('date-start')
    date_end = request.form.get('date-end')
    # Check if that period is in revenues, outgoings and savings
    check_period_revenues = db.execute(
        "SELECT * FROM revenues WHERE date_start = ? and date_end = ?",
        date_start,date_end
    )
    check_period_outgoings = db.execute(
        "SELECT * FROM outgoings WHERE date_start = ? and date_end = ?",
        date_start,date_end
    )
    check_period_savings = db.execute(
        "SELECT * FROM savings WHERE date_start = ? and date_end = ?",
        date_start,date_end
    )
    # If that period is in the tables delte first in revenues then in outgoings and last in periods
    if len(check_period_revenues) > 0 and len(check_period_outgoings) > 0 and len(check_period_savings) > 0:
        db.execute("DELETE FROM revenues WHERE date_start = ? AND date_end = ?",
                   date_start, date_end)
        db.execute("DELETE FROM outgoings WHERE date_start = ? AND date_end = ?",
                   date_start, date_end)
        db.execute("DELETE FROM savings WHERE date_start = ? AND date_end = ?",
                   date_start, date_end)

        db.execute(
            "DELETE FROM periods WHERE date_start = ? AND date_end = ?",
            date_start, date_end
        )

    # get the totals of budgets and reals values for table revenues per period
    total_revenues = db.execute(
        "select sum(budget) as revenues_budget, sum(real) as revenues_real from revenues WHERE id_user = ? group by date_start, date_end order by date_start, date_end;",
        session["user_id"]
    )
    print(total_revenues)
    # get the totals of budgets and reals values for table outgoings per period
    total_outgoings = db.execute(
        "select sum(budget) as outgoings_budget, sum(real) as outgoings_real from outgoings WHERE id_user = ? group by date_start, date_end order by date_start, date_end;",
        session["user_id"]
    )
    # get the totals of budgets and reals values for table savings per period
    total_savings = db.execute(
        "select sum(budget) as savings_budget, sum(real) as savings_real from savings WHERE id_user = ? group by date_start, date_end order by date_start, date_end;",
        session["user_id"]
    )
    # get the table periods WHERE date_start = ? and date_end = ? total_revenues[3],total_revenues[4]
    periods = db.execute(
        "select p.date_start, p.date_end from users as u join savings as r on u.id_user=r.id_user join periods as p on r.date_start = p.date_start where u.id_user = ? order by p.date_start, p.date_end;",
        session["user_id"]
    )

    # create a list to concatenate all the values
    totals=[]

    # for to append the list totals with base table periods
    for i in range(len(periods)):
        # check that i don´t oversize beyond revenues
        if i < len(total_revenues):
            # add totals revenues per period to periods
            periods[i].update(total_revenues[i])
            # get the values of total_budget and total_real for revenues
            # and check if there are nulls
            if total_revenues[i]["revenues_budget"] != None:
                revenues_budget = float(total_revenues[i]["revenues_budget"])
            else:
                revenues_budget = 0

            if total_revenues[i]["revenues_real"] != None:
                revenues_real = float(total_revenues[i]["revenues_real"])
            else:
                revenues_real = 0
        else:
            revenues_budget = 0
            revenues_real = 0

        # check that i don´t oversize beyond outgoings
        if i < len(total_outgoings):
            # add totals outgoings to periods
            periods[i].update(total_outgoings[i])
            # get the values of total_budget and total_real for outgoings
            # and check if there are nulls
            if total_outgoings[i]["outgoings_budget"] != None:
                outgoings_budget = float(total_outgoings[i]["outgoings_budget"])
            else:
                outgoings_budget = 0

            if total_outgoings[i]["outgoings_real"] != None:
                outgoings_real = float(total_outgoings[i]["outgoings_real"])
            else:
                outgoings_real = 0
        else:
            outgoings_budget = 0
            outgoings_real = 0

        # check that i don´t oversize beyond savings
        if i < len(total_savings):
            # add totals savings to periods
            periods[i].update(total_savings[i])

        # check if the total_budget is positive
        if revenues_budget >= outgoings_budget:
            periods[i].update({"total_budget":revenues_budget - outgoings_budget})
        else:
            periods[i].update({"total_budget":("-" + str(outgoings_budget - revenues_budget))})

        # check if the total_real is positive
        if revenues_real >= outgoings_real:
            periods[i].update({"total_real":revenues_real - outgoings_real})
        else:
            periods[i].update({"total_real":("-"+str(outgoings_real - revenues_real))})

        totals.append(periods[i])

    return render_template("dashboard.html", username=username, country=country, currency=currency,
                           revenues=revenues, outgoings=outgoings, savings=savings,totals=totals)


@app.route("/budget", methods=["GET", "POST"])
@login_required
def budget():
    # User reached route via POST (as by submitting a form via POST)
    if request.method == "POST":
        date_start = request.form.get('date-start')
        date_end = request.form.get('date-end')

        # Check if the period dates exist in periods
        dates = db.execute(
                "SELECT * FROM periods WHERE date_start = ? AND date_end = ?",
                date_start, date_end
            )

        # If there isn't period on periods
        if len(dates) != 1:
            if date_start and date_end:
                db.execute(
                    "INSERT INTO periods(date_start,date_end) VALUES(?,?)",
                    date_start, date_end
                )

        categories_revenues = request.form.getlist('category-revenue')
        descriptions_revenues = request.form.getlist('description-revenue')
        budgets_revenues = request.form.getlist('budget-revenue')

        # insert the data of revenues into the database
        for i in range(len(categories_revenues)):
            if categories_revenues[i] and descriptions_revenues[i] and budgets_revenues[i] and date_start and date_end:
                # Check row already exist
                row = db.execute(
                    "SELECT id_revenue FROM revenues WHERE category_revenue = ? AND description = ? AND date_start = ? AND date_end = ? AND id_user = ?",
                    categories_revenues[i],descriptions_revenues[i],date_start,date_end,session["user_id"]
                )

                if row:
                    db.execute(
                        "UPDATE revenues SET budget = ? WHERE id_revenue = ?",
                        budgets_revenues[i], row[0]["id_revenue"]
                    )

                else:
                    db.execute(
                        "INSERT INTO revenues(category_revenue,description,budget,date_start,date_end,id_user) VALUES(?,?,?,?,?,?)",
                        categories_revenues[i],descriptions_revenues[i],budgets_revenues[i],date_start,date_end,session["user_id"]
                        )


        categories_outgoings = request.form.getlist('category-outgoing')
        descriptions_outgoings = request.form.getlist('description-outgoing')
        budgets_outgoings = request.form.getlist('budget-outgoing')


        # insert the data of outgoings into the database
        for i in range(len(categories_outgoings)):
            if categories_outgoings[i] and descriptions_outgoings[i] and budgets_outgoings[i] and date_start and date_end:
                # Check row already exist
                row = db.execute(
                    "SELECT id_outgoing FROM outgoings WHERE category_outgoing = ? AND description = ? AND date_start = ? AND date_end = ? AND id_user = ?",
                    categories_outgoings[i],descriptions_outgoings[i],date_start,date_end,session["user_id"]
                )

                if row:
                    db.execute(
                        "UPDATE outgoings SET budget = ? WHERE id_outgoing = ?",
                        budgets_outgoings[i], row[0]["id_outgoing"]
                    )

                else:
                    db.execute(
                        "INSERT INTO outgoings(category_outgoing,description,budget,date_start,date_end,id_user) VALUES(?,?,?,?,?,?)",
                        categories_outgoings[i],descriptions_outgoings[i],budgets_outgoings[i],date_start,date_end,session["user_id"]
                    )

        # check if there is savings
        if request.form.get('budget-saving'):
            budget_savings = float(request.form.get('budget-saving'))
        else:
            budget_savings = 0

        # check for dates and values
        check_saving = False;

        # check if there is dates
        if date_start and date_end:
            # consult to the databse for the total revenues in this dates
            total_revenues = db.execute(
                "SELECT SUM(budget) AS total_revenue FROM revenues WHERE date_start = ? AND date_end = ?",
                date_start, date_end
            )
            # check if there is revenues on this dates
            if len(total_revenues) != 1:
                total_revenues = 0
            # convert the total into a number instead of a list
            total_revenues = total_revenues[0]["total_revenue"]
        else:
            total_revenues = 0
            check_saving = True;

        if date_start and date_end:
            # consult to the databse for the total outgoings in this dates
            total_outgoings = db.execute(
                "SELECT SUM(budget) AS total_outgoing FROM outgoings WHERE date_start = ? AND date_end = ?",
                date_start, date_end
            )
            # check if there is outgoings on this dates
            if len(total_outgoings) != 1:
                total_outgoings = 0
            # convert the total into a number instead of a list
            total_outgoings = total_outgoings[0]["total_outgoing"]
        else:
            total_outgoings = 0
            check_saving = True;
        # calculate the total of revenues and outgoings
        total_budget = total_revenues - total_outgoings
        # check if there is money to save in this period
        check_saving = budget_savings > total_budget;

        # check that there is enogh in the budget for saving
        if request.form.get('budget-saving') and check_saving:
            # check if there are dates
            if date_start and date_end:
                return apology("invalid saving", 403)
            else:
                return apology("invalid dates", 403)

        # insert the data of savings into the database
        if request.form.get('budget-saving'):
            if date_start and date_end:
                # Check row already exist
                row = db.execute(
                    "SELECT id_saving FROM savings WHERE date_start = ? AND date_end = ? AND id_user = ?",
                    date_start,date_end,session["user_id"]
                )

                if row:
                    db.execute(
                        "UPDATE savings SET budget = ? WHERE id_saving = ?",
                        budget_savings, row[0]["id_saving"]
                    )

                else:
                    db.execute(
                        "INSERT INTO savings(budget,date_start,date_end,id_user) VALUES(?,?,?,?)",
                        budget_savings,date_start,date_end,session["user_id"]
                    )

        return redirect("/dashboard")

    return render_template("budget.html")

@app.route("/real", methods=["GET", "POST"])
@login_required
def real():
    if request.method == "POST":
        date_period = request.form.get("date")
        periods = db.execute("SELECT * FROM periods")
        for i in range(len(periods)):
            if date_period >= periods[i]["date_start"] and date_period <= periods[i]["date_end"]:
                break;
            else:
                return apology("date is not in any period", 403)
        revenue_category = request.form.getlist("revenue-category")
        revenue_description = request.form.getlist("revenue-description")
        # Check if there are revenue category and description that match in the database
        for i in range(len(revenue_category)):
            check_revenue=db.execute(
                "SELECT * FROM revenues WHERE category_revenue=? AND description=?",
                revenue_category[i],revenue_description[i]
            )
            if not check_revenue:
                return apology("Invalid revenue category-description", 400)

        revenues_real = request.form.getlist("real-revenue")
        # check if there is a value for real revenue
        for i in range(len(revenues_real)):
            if not revenues_real[i]:
                print(revenue_category, revenue_description, revenues_real, today)
            else:
                db.execute(
                    "UPDATE revenues SET real=?, date_revenue=? WHERE id_user=? AND category_revenue=? AND description=?",
                    revenues_real[i], date_period, session["user_id"], revenue_category[i], revenue_description[i]
                )

        outgoing_category = request.form.getlist("outgoing-category")
        outgoing_description = request.form.getlist("outgoing-description")
        # Check if there are outgoing category and description that match in the database
        for i in range(len(outgoing_category)):
            check_outgoing=db.execute(
                "SELECT * FROM outgoings WHERE category_outgoing=? AND description=?",
                outgoing_category[i],outgoing_description[i]
            )
            if not check_outgoing:
                return apology("Invalid outgoing category-description", 400)

        real_outgoing = request.form.getlist("real-outgoing")
        # check if there is a value for real outgoing
        for i in range(len(real_outgoing)):
            if not real_outgoing[i]:
                print(outgoing_category, outgoing_description, real_outgoing, today)
            else:
                db.execute(
                    "UPDATE outgoings SET real=?, date_outgoing=? WHERE id_user=? AND category_outgoing=? AND description=?",
                    real_outgoing[i], date_period, session["user_id"], outgoing_category[i], outgoing_description[i]
                )

        # check if there is savings
        if request.form.get('real-saving'):
            real_savings = request.form.get('real-saving')
            db.execute(
                    "UPDATE savings SET real=?, date_saving=? WHERE id_user=?",
                    real_savings, date_period, session["user_id"]
                )
        return redirect("/dashboard")

    revenues_categories = db.execute(
        "SELECT category_revenue FROM revenues WHERE id_user=? GROUP BY category_revenue",session["user_id"]
    )
    revenues = db.execute(
        "SELECT * FROM revenues WHERE id_user=? GROUP BY category_revenue",session["user_id"]
    )
    outgoings_categories = db.execute(
        "SELECT category_outgoing FROM outgoings WHERE id_user=? GROUP BY category_outgoing",session["user_id"]
    )
    outgoings = db.execute(
        "SELECT * FROM outgoings WHERE id_user=? GROUP BY category_outgoing",session["user_id"]
    )
    return render_template("real.html", revenues_categories=revenues_categories,
                           revenues=revenues, outgoings_categories=outgoings_categories,
                           outgoings=outgoings, today=today)


@app.route("/login", methods=["GET", "POST"])
def login():
    """Log user in"""

    # Forget any user_id
    session.clear()

    # User reached route via POST (as by submitting a form via POST)
    if request.method == "POST":
        # Ensure username was submitted
        if not request.form.get("username"):
            return apology("must provide username", 403)

        # Ensure password was submitted
        elif not request.form.get("password"):
            return apology("must provide password", 403)

        # Query database for username
        rows = db.execute(
            "SELECT * FROM users WHERE username = ?", request.form.get("username")
        )

        # Ensure username exists and password is correct
        if len(rows) != 1 or not check_password_hash(
            rows[0]["hash"], request.form.get("password")
        ):
            return apology("invalid username and/or password", 403)

        # Remember which user has logged in
        session["user_id"] = rows[0]["id_user"]

        # Redirect user to home page
        return redirect("/dashboard")

    # User reached route via GET (as by clicking a link or via redirect)
    else:
        return render_template("login.html")


@app.route("/logout")
def logout():
    """Log user out"""

    # Forget any user_id
    session.clear()

    # Redirect user to login form
    return redirect("/")


@app.route("/password", methods=["GET", "POST"])
@login_required
def password():
    """Change Password"""
    # User reached route via POST (as by submitting a form via POST)
    if request.method == "POST":
        # Ensure password was submitted
        if not request.form.get("new-password"):
            return apology("must provide password", 400)

        # Ensure confirmation was submitted
        elif not request.form.get("confirmation"):
            return apology("must provide password confirmation", 400)

        # Query database for change password
        db.execute(
            "UPDATE users SET  hash = ? WHERE id_user = ?", generate_password_hash(
                request.form.get("new-password")), session["user_id"]
        )

        return redirect("/logout")

    else:
        return render_template("password.html")


@app.route("/register", methods=["GET", "POST"])
def register():
    """Register user"""
    # User reached route via POST (as by submitting a form via POST)
    if request.method == "POST":
        # Ensure username was submitted
        if not request.form.get("username"):
            return apology("must provide username", 400)

        # Ensure country was submitted
        elif not request.form.get("country"):
            return apology("must provide country", 400)

        # Ensure country was submitted
        elif not request.form.get("currency"):
            return apology("must provide currency", 400)

        # Ensure password was submitted
        elif not request.form.get("password"):
            return apology("must provide password", 400)

        # Ensure confirmation was submitted
        elif not request.form.get("confirmation"):
            return apology("must provide password confirmation", 400)

        elif request.form.get("password") != request.form.get("confirmation"):
            return apology("Passwords do not match", 400)

        country=db.execute("SELECT name_country FROM countries WHERE name_country=?", request.form.get("country").lower())
        if len(country) == 0:
            db.execute("INSERT INTO countries(name_country,currency) VALUES(?,?)", request.form.get("country").lower(), request.form.get("currency").lower())
            id_country = db.execute("SELECT id_country FROM countries WHERE name_country=?", request.form.get("country").lower())

        else:
            id_country = db.execute("SELECT id_country FROM countries WHERE name_country=?", request.form.get("country").lower())


        # Query database for username
        try:
            db.execute(
                "INSERT INTO users(id_country,username,hash) VALUES(?,?,?)", id_country[0]["id_country"], request.form.get(
                    "username"), generate_password_hash(request.form.get("password"))
            )
        except ValueError:
            return apology("username already exist", 400)

        # Query database for username
        rows = db.execute(
            "SELECT * FROM users WHERE username = ?", request.form.get("username")
        )

        # Ensure username exists and password is correct
        if len(rows) != 1 or not check_password_hash(
            rows[0]["hash"], request.form.get("password")
        ):
            return apology("invalid username and/or password", 403)

        # Remember which user has logged in
        session["user_id"] = rows[0]["id_user"]

        return redirect("/dashboard")

    else:
        return render_template("register.html")

