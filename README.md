
# How many apps involved

# Brief explanation each apps

# Architecture/Layer diagram for each of the apps including the middleware


# List of URL Endpoints for Middleware RESTful/SOAP/Socket

- **User Login**
  - `http://localhost/webServiceProjectDAD/login.php`

- **Retrieve All Orders**
  - `http://localhost/webServiceProjectDAD/getAllOrders.php`

- **Populate Product List**
  - `http://localhost/webServiceProjectDAD/populateProductList.php?category=`

- **Update Product Quantity**
  - `http://localhost/webServiceProjectDAD/updateProductQuantity.php`

- **Get Product Quantity**
  - `http://localhost/webServiceProjectDAD/getProductQuantity.php?productName=`

- **Add Order**
  - `http://localhost/webServiceProjectDAD/addOrder.php`

- **Retrieve Customer Orders**
  - `http://localhost/webServiceProjectDAD/getCustomerOrders.php?username=`

- **Update Order Status**
  - `http://localhost/webServiceProjectDAD/updateOrderStatus.php`

- **Generate Reports**
  - `http://localhost/webServiceProjectDAD/report.php?action=`


# Functions/Features in the middleware

## Order Management

1. **Add Order**
   - **Endpoint:** `addOrder.php`
   - **Function:** Adds a new order with details like customer name, phone number, address, items ordered, and total price.
   - **Initial status** of the order is set to "PENDING".
   - **Returns:** A success or error status in JSON format.

2. **Retrieve All Orders**
   - **Endpoint:** `getAllOrders.php`
   - **Function:** Retrieves all orders or filters them by status.
   - **Accepts:** An optional `status` parameter.
   - **Returns:** A list of orders or an error if no orders match the criteria.

3. **Retrieve Customer Orders**
   - **Endpoint:** `getCustomerOrders.php`
   - **Function:** Retrieves orders for a specific customer, optionally filtered by status.
   - **Requires:** A `username` parameter.
   - **Returns:** A list of orders or an error if no orders match the criteria.

4. **Update Order Status**
   - **Endpoint:** `updateOrderStatus.php`
   - **Function:** Updates the status of a specific order.
   - **Requires:** `orderID` and `status` parameters.
   - **Returns:** A success or error message in JSON format.


  ## Product Management

1. **Get Product Quantity**
   - **Endpoint:** `getProductQuantity.php`
   - **Function:** Retrieves the quantity of a specified product.
   - **Requires:** A `productName` parameter.
   - **Returns:** The product quantity or an error if the product is not found.

2. **Update Product Quantity**
   - **Endpoint:** `updateProductQuantity.php`
   - **Function:** Updates the quantity of a specified product by decreasing it.
   - **Requires:** `productName` and `quantity` parameters.
   - **Returns:** A success or error message if the product is not found or quantity is insufficient.

3. **Populate Product List**
   - **Endpoint:** `populateProductList.php`
   - **Function:** Retrieves a list of products in a specified category.
   - **Requires:** A `category` parameter.
   - **Returns:** Product names and prices or an error if the query fails.


## User Management

1. **User Login**
   - **Endpoint:** `login.php`
   - **Function:** Authenticates a user and returns their role.
   - **Requires:** `username` and `password` parameters.
   - **Returns:** A success or error message and includes the user's role if authentication is successful.


## Reporting

1. **Generate Reports**
   - **Endpoint:** `report.php`
   - **Function:** Generates various reports based on the action parameter.
   - **Accepts:** An `action` parameter with values like `totalItems`, `totalDelivered`, `totalInProcess`, `totalUsers`, and `totalSales`.
   - **Executes:** The corresponding query and returns the report data or an error for invalid actions.

# The database and tables involve in the projects

Database name: projectdad

Database tables:
![image](https://github.com/MalKim007/pharmacy-ordering-system/assets/118043542/0983f331-c2fa-4899-9d31-cdb067487a97)


