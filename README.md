# My Personal Project

## An app that keep track of items in stock of a grocery store
A stock tracking application for a small grocery store so they can keep track of their stocks efficiently. Every time a
delivery for a new item arrive in store, the store manager, or anyone who is in charge of receive the order that time 
input the item that they received, how many items there are, whether the item is shelf-stable, if the item is not 
shelf-stable, how long the item would last, the item price per single unit sold. The item then is added to a list of
grocery items in the grocery store. This project is interesting to me since I'm currently volunteering for the FoodHub 
Market and one of the challenge that I'm seeing at the market is keeping track of the item in stock at the store, and 
their expiry date, while sometimes we can clearly see that items are out of stock, but with multiple items to keep 
track, some item get lost in the back of the store, and we don't notice that the item is out of stock until a customer 
ask about those item. In addition, keeping track of expiry date of some of these item is also important, it is to 
ensure that we don't sell molded or expired product to our customer. Hopefully this app can offset some problems
that we face as a small student-run grocery store. 


- **X is: *GroceryItem*** (with feature: name, brand, price, amount, isShelfStable?, expiryDate, boughtPrice)
- **Y is *List \<GroceryItem>***: It could be a variety of list of Grocery Items created as the manager would like with 
different classification such as: Produce, CannedFood, Sauce, Vegetables, Dairy, etc.

## User Stories:
- As a user, I want to add StoreItem to List\<StoreItem> that fits their classification
- As a user, I want to remove StoreItem from List\<StoreItem> based on my chosen Section.
- As a user, I want to view the StoreItems, and their feature in my List
- As a user, I want to add amount of the same GroceryItem
- As a user, I want to reduce amount of the same GroceryItem
- As a user, I want to see the expiry date of my GroceryItem.
- As a user, I want to see the price to sell the product.
- As a user, I want to save all the items that have been added to each section, while using, 
and before closing the program
- As a user, I want to have the option of either start out blank, or load the data that I have saved before.

# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by open the Section tab in the menu-bar, 
and choose the section that you would wan to add your Xs into your Y, and click on **Add Item** button, 
and input information about your new X. When you're done, click on **Add Item** button in the window that you just 
input your information about your new X
- You can generate the second required action related to adding Xs to a Y by open the Section tab in the menu-bar,
and choose the section that you would want to add your Xs into your Y by clicking on Xs-name and click **Remove Item** button
- You can locate my visual component by clicking on **Load** in the menu-bar or **Save** in the menu bar, 
where a checkmark-icon appear in a Dialog.
- You can save the state of my application by clicking the **Save** in the menu-bar
- You can reload the state of my application by clicking the **Load** in the menu-bar.

### References:
- The application UI is based on https://github.students.cs.ubc.ca/CPSC210/TellerApp TellerApp.java in ui package.
- JsonReader and JsonWriter is based on https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo JsonReader.java
and JsonWriter.java in persistence package.
- Image for Java logo taken from: https://logos-download.com/10695-java-logo-download.html
- Image for checkmark taken from: https://clipground.com/images/check-logo-8.png


  