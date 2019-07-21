@SampleTest
Feature: Information icon
Description: This test is just a sample of how to implement cucumber in BDD test frameworks

@US1_scenario1
Scenario Outline: Validate that while using the KiwiSaver Retirement Calculator user is able to see appropriate information when information icon is clicked
Given On home page, user navigates to KiwiSaver Retirement Calculator page
When On KiwiSaver Retirement Calculator page, user clicks on the information icon besides Current age input field
Then Validate that appropriate message '<Age_message>' is displayed below the input field

Examples:
| Age_message          |
| Information_messages |

@US1_scenario2
Scenario Outline: Validate that the KiwiSaver Retirement Calculator users are able to calculate what their KiwiSaver projected balance would be at retirement
Given On home page, user navigates to KiwiSaver Retirement Calculator page
When On KiwiSaver Retirement Calculator page, user enters values against all input fields '<Input_field_values>'
And user clicks on View your KiwiSaver retirement projections button
Then validate that kiwisaver balance '<RetirementBalance>' is displayed

Examples:

| Input_field_values			  |	RetirementBalance |
| Input_Field_Values_Employed     | 278,983           |
| Input_Field_Values_SelfEmployed | 212,143           |
| Input_Field_Values_NotEmployed  | 168,278           |