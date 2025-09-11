# 🛒Online Store


A **console-based Online Store project** built with **Hibernate ORM** and **MySQL**.  
It demonstrates how to manage **Users, Categories, Products, and Orders** with  
**CRUD operations, transactions, and relationships** (One-to-Many, Many-to-Many).  

---

## 🚀 Features
- Add, view, update, and delete **Users**
- Manage **Product Categories**
- Manage **Products**
- Place and manage **Orders**
- Demonstrates **Hibernate Transactions**
- Implements **One-to-Many** and **Many-to-Many** relationships



---

## 🛠️ Tech Stack
- **Java**
- **Hibernate ORM**
- **MySQL**
- **Maven**

---

## 📂 Project Structure

src/main/java/org/example/
│── entity/ # Hibernate entity classes (User, Category, Product, Order, OrderItem)
│── util/ # HibernateUtil.java (SessionFactory setup)
│── App.java # Console-based menu for CRUD operations


---

## ⚙️ Setup & Run
1. Clone this repo:
   ```bash
   git clone https://github.com/your-username/hibernate-online-store.git

   Configure hibernate.cfg.xml with your MySQL credentials.

Create a MySQL database:

CREATE DATABASE online_store;


Run the project from App.java.


🎯 Learning Outcomes

Hibernate CRUD operations

Transactions for safe DB operations

Handling One-to-Many and Many-to-Many relationships

Building a real-world e-commerce backend simulation

