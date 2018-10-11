!--Meta:
!--
!--Narrative:
!--As a user
!--I want to perform an action
!--So that I can achieve a business goal
!--
!--Scenario: scenario description
!--Given a system state
!--When I do something
!--Then system is in a different state

Narrative:
This story covers basic tests of signup

Scenario: Type invalid year
Given I open signup page
When I set month "December"
And I set day "20"
And I set year "85"
And I check share
Then I see error "Please enter a valid year."
And I don not see error "When were you born?"

Scenario: Type invalid email
Given I open signup page
When I type mail "aaa@aaa.by"
And I type confirmation mail "aaa@bbb.by"
And I type name "Name Test"
And I click signup button
Then I see error "Email address doesn't match."

Scenario: Sign up with empty password
Given I open signup page
When I type mail aaa@aaa.by
And I type confirmation mail "aaa@aaa.by"
And I type name "TestName"
And I click signup button
Then I see error "Please choose a password."

Scenario: Type invalid values
Given I open signup page
When I type mail aaa@aaa.by
And I type confirmation mail "aaa@aaa.by"
And I type name "TestName"
And I type password "qqqqqqqqq"
And I select sex "Male"
And I unchecked share
And I click signup button
Then I see "4" errors
And I see that "3" has text "Please enter a valid year."