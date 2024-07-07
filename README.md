# Pharmacy Ordering System

This project is for subject Distributed Application Development...
<br><br>
### Team Members

| Name             | Matric Number     |
| ---------------- | --------------------- |
| Muhammad Akmal Hakim Hishamuddin  | B032310162  |
| Siti Balqis bin Mat Muharam  | B032310135   |
| Muhammad Zulhelmi bin Noor Afendi  | B032310217  |
| Ahmad Naqiuddin bin Mohamad  | B032310002  |

<br><br>
## How many apps involved

<div align="justify">
  
We have two main applications within our system.

The first application focuses on customer transactions. When a customer purchases an item, the transaction is recorded and stored in our database. Both the item details and user information are maintained in this database to ensure accurate record-keeping. Customers can also use this application to check the status of their purchased items at any time.

The second application is designed for administrative purposes. Admins have access to view all orders placed by customers. They can update the status of these orders, which customers can then track in real-time. Additionally, admins can generate and view comprehensive sales reports and monitor the statuses of all orders to ensure efficient order management and fulfillment.

Together, these applications streamline the transaction process for customers and provide robust administrative tools for managing orders and sales.

</div>

<br><br>
## Brief explanation each apps

### Customer Flow

#### Choose Item Process

1. **Select Menu:** 
   - The customer clicks on the "Choose Item" option in the menu option.

2. **Browse by Category:** 
   - The customer can browse and select items based on different categories.

3. **Specify Quantity:** 
   - Once the customer selects the desired item, they can enter the quantity for each item.

4. **Enter Personal Information:** 
   - After finalizing the item selection and quantity, the customer needs to enter their phone number and address.

5. **Submit Order:** 
   - The customer clicks the "Submit" button to proceed with their purchase.

6. **View Receipt and Confirmation:** 
   - A receipt will be displayed, showing the total price of the items. A confirmation message will also pop up, indicating whether the purchase was successful.

<br>

#### Check History Process

1. **Select Menu:** 
   - The customer clicks on the "Manage History" option in the menu.

2. **View All History:** 
   - The customer can view the entire order history, although the list may not be sorted according to their preferences.

3. **Search Order Status:** 
   - The customer can filter the order history by selecting a specific order status and clicking the "Search" button.

4. **Filtered List:** 
   - The list will update to display orders matching the selected status.
  
<br>

### Admin Flow
#### Navigate to Check Order Page

- **Navigate to Check Order Page:**
  - Admin clicks the "Check Order" button.

#### Check Order Page

- **Check Order Page:**
  - Admin is directed to the Check Order page.
  - The page displays a table listing the orders.

#### Sorting Orders

- **Sorting Orders:**
  - Admin uses the "Sort By" dropdown menu.
  - Admin selects a sorting criterion such as "Pending, Packing, In Transit, Out For Delivery, Delivered."
  - Admin clicks the "Apply" button to sort the orders accordingly.

- **Efficient Management:**
  - This process allows the admin to manage orders efficiently by sorting and filtering as needed.

#### Update Status

  - The "Sort By" dropdown menu offers a range of sorting options:
    - All
    - Pending
    - Packing
    - In Transit
    - Out For Delivery
    - Delivered
  - The table displays orders with various statuses, including Pending, Packing, Delivered, In Transit, and more detailed order information.
  - When "Delivered" is selected in the "Sort By" dropdown, the table displays only orders with the "Delivered" status, demonstrating how the sorting function filters the orders.

#### Order Details Section

- **Order Details Section:**
  - The admin can view and update individual orders in the order details section:
    - **Customer Name Field:** Shows the customer's name.
    - **Items Field:** Shows the items ordered.
    - **Order ID Field:** Shows the Order ID.
    - **Status Dropdown:** Currently set to "In Transit" with options to change the status to Pending, Packing, In Transit, Delivered, or Canceled.
    - **Update Button:** Updates the order details.
    - **Clear Button:** Clears the order details fields.
   
#### Analysis Report

- **Total Item:**
   - Count the total items from the database that have been registered.

- **Total User:**
   - Count the total users from the database that have been registered.

- **Total Delivered:**
   - Count the number of total deliveries that have successfully been delivered to the customer's doorstep.

- **Total Inprocess:**
   - Count the number of total deliveries that are in process of being delivered to the customer's doorstep.

- **Total Sales:**
   - Count the total sales that have been ordered by the customer.
<br><br>
## Architecture/Layer diagram for each of the apps including the middleware

![WhatsApp Image 2024-07-07 at 23 14 05_7d20a87e](https://github.com/MalKim007/pharmacy-ordering-system/assets/118043542/e0080400-4ab3-4b46-a3bd-95cc66c60ea9)

<br><br>
## List of URL Endpoints for Middleware RESTful

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

<br><br>
## Functions/Features in the middleware

### Order Management

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


  ### Product Management

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


### User Management

1. **User Login**
   - **Endpoint:** `login.php`
   - **Function:** Authenticates a user and returns their role.
   - **Requires:** `username` and `password` parameters.
   - **Returns:** A success or error message and includes the user's role if authentication is successful.


### Reporting

1. **Generate Reports**
   - **Endpoint:** `report.php`
   - **Function:** Generates various reports based on the action parameter.
   - **Accepts:** An `action` parameter with values like `totalItems`, `totalDelivered`, `totalInProcess`, `totalUsers`, and `totalSales`.
   - **Executes:** The corresponding query and returns the report data or an error for invalid actions.

<br><br>
## The database and tables involve in the projects

### Database name: projectdad

### Database tables:

![image](https://github.com/MalKim007/pharmacy-ordering-system/assets/118043542/0983f331-c2fa-4899-9d31-cdb067487a97)


