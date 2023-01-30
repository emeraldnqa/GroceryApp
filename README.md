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


- **X is: *GroceryItem*** (with feature: price, amount, isShelfStable?, expiryDate, boughtPrice)
- **Y is *List \<GroceryItem>***: It could be a variety of list of Grocery Items created as the manager would like with 
different classification such as: Produce, CannedFood, Sauce, Vegetables, Dairy, etc.

## User Stories:
- As a user, I want to add GroceryItem to List\<GroceryItem> that fits their classification,
- As a user, I want to remove GroceryItem to List\<GroceryItem> that fits their classification
- As a user, I want to view the GroceryItems, and their feature in my List
- As a user, I want to set a new price for the GroceryItem if needed
- As a user, I want to add amount of the same GroceryItem
- As a user, I want to reduce amount of the same GroceryItem
- As a user, I want to see the expiry date of my GroceryItem.
- As a user, I want to find the price to sell the product gain 5% of profit for the product that we sell.

  