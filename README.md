# receipt-super-saver

I have a large family for 4 boys and spend a lot of my income on food and household related purchases.
Mostly these are done in one of the major supermarket chains in Sweden. 
As I am using my membership to get discounts I have known for a long time that they keep track of quite detailed purchase information.
Online I can do stuff like manage purchase lists, check discounts and the like.
However there is no function that lists all my purchases based on category or item!

- This information would be great in order to try to reduce overall cost. 
It's common prioritization really - check where most of the money are spent and try to reduce this.

Sadly no luck with this online feature......

Until GDPR kicked into effect forcing the retailer to allow me to get a look at all the data they keep - this includes purchase history.

Said and done, I requested this data and created a simple framework for importing and converting this data into a common format.
The project is written in Java and does not rely on any database for portability.
Import into your favourite IDE using pom.xml

Currently there is support for ICA receipt data (made up of 2 files)
The input data needs to be in JSON, so the first step is to convert from XML. 
Remove top level root when converting. I used this converter:
http://convertjson.com/xml-to-json.htm

- Currently there is no command line option to configure the app.

Next step is to put json files somewhere on the filesystem and point the main program to it.
Then recompile and run your app.

How to request receipt data from ICA.

- You must have an online login, this is usually done using 'Mobilt BankID'.
- Login to your account.
- Go to 'Inställningar' by selecting your account (name)
- Select 'Mina data' and then 'Hantera Dina Data'
- Click 'Portera din data' and sign the request - you will get the xml files sent to you by email within a day or two.
