# project4
It is used to simulate one grocery Checkout counter, and see what happened in the next few hours
This is my school's project

The Environment to be Modeled


We will assume the following adjustable system parameters:

A variable number of checkout lanes
Possibility to have one or two lanes restricted to 10 items or fewer
Average inter-arrival rate of shoppers at the checkout 
We will assume the following constraints:

Shoppers will be passive, but they will contain data such as:
      1) time they entered a checkout line
      2) number of items in their cart (determined statistically, see below)

Arrival of a shopper at a checkout lane will be provided by an arrival class that will generate arrivals for all the lanes (determined statistically, see below)
Checkout time is determined according to number of items in the shopping cart (see below)
Customer bagging increases the per-customer checkout time over employee bagging (see formula below)
System Elements

Simulation of the system is achieved by creating classes to model the behavior of each element of the system as well as each activity (event) that occurs in the system. There is NOT an overall controller that calls for actions to happen at particular times except for a main driver loop that runs queued events until time runs out. Each element in the system models its own behavior and interactions with other elements in the system.

For the Grocery Simulation, we have:

Elements and the Classes to Represent Them

* Shoppers (Shopper) - passive and contain data only
* Arrival generator (ShopperMaker)
* Checkout Lanes (Checkers)
* agenda (PQ)

Events (actions)

Each NON-passive element class has an Event object class associated with it, or in the case of the ShopperMaker where there is only one instance, the class can also be the event. The associated event contains the run() method (as discussed in lecture) that simulates the specific behavior of that class.

* CheckerEvent (occurs each time a Shopper finishes checkout and leaves)
* ShopperEvent (one occurs for each Shopper entering the checkout system)

Let's look at each of these components, and see what they should contain. Please note that you may need to include other information, but what is given here is considered fundamental information to model the system.

Shopper

This is instantiable once for each Shopper entering the checkout system. As described above, this class is passive; it contains only data. In addition to arrival time and number of items in the cart, you may decide to put more data in the Shopper, but at least those two data items are necessary.

ShopperMaker

This is instantiable once for each Shopper creation event. Note that since one ShopperMaker will generate all of the Shoppers for the simulation, there is no need for multiple instances of this class--only multiple events. So, each instantiation of ShopperMaker will represent each ShopperMaker event. The class can be defined as:

    public class ShopperMaker implements Event {

This is similar to how the CarMaker class was implemented as described in lecture. ShopperMaker will create reschedule itself (using the agenda), create a Shopper, decide how many groceries the Shopper will have and 
place the Shopper in a queue in front of a Checker.

Checker

This is instantiable once for each checkout line at the store. This is the representation of a checkout counter. Each Checker will have a queue associated with it, and most likely an index so that you can refer to a particular Checker. It might be best to create an array of Checker to hold each the checkout line instances. At minimum, the Checker class will have a queue to hold waiting Shoppers, and a boolean flag that indicated whether it is busy or not.

CheckerEvent

This implements the Event interface. A CheckerEvent is created for each completion of a customer's checkout. When a customer leaves with their bagged groceries, the CheckerEvent causes the Checker that created it to look to its queue to see if there is a Shopper waiting. If there is a Shopper queued, the associated Checker will create a new CheckerEvent and schedule it (via the agenda) for the future at time depending on the number of items in the queued Shopper's cart and whether customer or employee bagging is done. (The CheckerEvent will need to have some way of associating with the Checker that initiated it.)

PQ

This is the priority queue (likely to be called the agenda within your code). This code is provided. You will need one instance of it for. This one instance will be used to schedule all events for your simulation.

Other Supporting Elements

A mentioned above, an array should probably be created to hold the instances of the Checker class. This can be done in the main driver where your simulation loop is found (refer to CarSim.java). You will probably want to also keep track of the busy and idle time of each Checker in a Statistics class.

Randomness

There are two places where you will need to use a random distribution to model features of the system. These are the arrival of Shoppers at the checkout area, and the selection of the number of grocery items in a Shopper's cart. Both are determined by method calls made in the run() method for the ShopperMaker.

For the arrival of Shoppers into the checkout area, you should start with an average inter-arrival rate of 1 Shopper every 30 seconds. But, you will want to run your model with both higher and lower demand by decreasing and 
increasing this inter-arrival time. To more realistically represent the pattern of arrival of Shoppers, we will introduce some randomness according to the table below (calculations shown using a 30 second inter-arrival time
as an example):

10% of the time: 75% above average arrival interval (30 + .75 * 30)
15% of the time: 50% above average arrival interval (30 + .50 * 30)
20% of the time: 20% above average arrival interval (30 + .20 * 30)
10% of the time: right at average arrival interval (30)
20% of the time: 20% below average arrival interval (30 - .20 * 30)
15% of the time: 50% below average arrival interval (30 - .50 * 30)
10% of the time: 75% below average arrival interval (30 - .75 * 30)

Shoppers also need to be given a randomized grocery item count for the their "shopping cart." We will do this by selecting a number in the range of 1..100 items, inclusive. However, certain ranges are more likely to occur 
than others. For example, not many shoppers purchase 91-100 items, but many purchase 21-30 items. The chart below weights the grocery count based on "observed patterns." The first column of the chart represents the number of shoppers out of every thirty, who purchase between 1-10 items, inclusive; the second column represents the number of shoppers out of every thirty who purchase between 11-20 items, inclusive, and so on, up to the last column which represents the number of shoppers out of every thirty who purchase 91-100 items, inclusive.

10 20 30 40 50 60 70 80 90 100
10 20 30 40 50 60 70 80 
10 20 30 40 50 60 70
     20 30 40 50
          30
          

There are 30 values in the above distribution. So, for example, one time out of 30, a shopper will purchase 91-100 items, but 4 times out of every 30, a shopper will purchase 11-20 items. This table can be implemented by placing each of the values shown in an array of 30 items. Then, a random number between 0 and 29, inclusive, can be used to select one of the values, x, from the array. Once x is chosen, then randomly choose a value between x - 9 and x, inclusive, and use that as the number of items purchased for a particular customer. By using this approach, all item counts between 1-100 will be possible, yet the array only needs to be of length 30.

Determining processing time. 

To determine the amount of checkout time it will take, we will use a formula based on the number of items in the shopping cart. The amount of checkout time is used to schedule a CheckerEvent.

That formula is:

* for employee bagging,
time to checkout a customer = 5 seconds * number of items in cart
* for shopper bagging,
time to checkout a customer = 9 seconds * number of items in cart

Assumptions:

1) Assume that shopper arrival distribution and number of items in a shopper's cart will use the distributions in the tables above.

2) Assume that the maximum number of checkout lanes is 12.

3) Assume that customers will always go to the lane with the shortest line.

4) Use the formula above for calculating the amount of time needed to check out a shopper.

5) When using express lanes, customers will always obey the signs. That is, they will not go in the express lane if they have more than 10 items.

Variables:

1) Load: the average inter-arrival rate of shoppers

2) Number of checkout lanes (1-12)

3) Number of express lanes for 1-10 items (0-11)

4) Employee bagging or shopper bagging
