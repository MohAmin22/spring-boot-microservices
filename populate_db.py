import mysql.connector
import random

# Connect to the MySQL database
db = mysql.connector.connect(
    host="localhost",
    user="root",
    password="Zainy@123",
    database="rating_db"
)

cursor = db.cursor()

n = 10000  # Number of tuples

for _ in range(n):
    movie_id = random.randint(1, 1000)
    user_id = random.randint(1, 1000)
    rating = random.randint(1, 1000)

    # Query to check if the composite key exists
    cursor.execute("SELECT * FROM ratings WHERE movie_id = %s AND user_id = %s", (movie_id, user_id))

    # If the query returns no rows, the composite key does not exist, so insert the new row
    if cursor.fetchone() is None:
        cursor.execute("INSERT INTO ratings (movie_id, user_id, rating) VALUES (%s, %s, %s)", (movie_id, user_id, rating))

# Commit the transaction
db.commit()

# Close the cursor and the connection
cursor.close()
db.close()