Feature: Shouty
	One person shout and another person listen
  Scenario: Listners are within range
   Given Tom is 15 meters away from Jerry
   When Jerry shouts with 'Free Movie Tickets'
   Then Tom should hear his message
   
 

 