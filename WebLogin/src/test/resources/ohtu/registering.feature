Feature: A new user account can be created if a proper unused username and password are given

  Scenario: creation succesful with correct username and password
    Given new user is selected
    When correct username "kalervo" and valid password "k4l3rv00tt1" are given
    Then user is created

  Scenario: creation fails with too short username and valid passord
    Given new user is selected
    When incorrect username "ka" and valid password "k4l3rv00tt1" are given
    Then user is not created and error "username should have at least 3 characters" is reported

  Scenario: creation fails with correct username and too short password
    Given new user is selected
    When correct username "kalervo" and invalid password "k4l3" are given
    Then user is not created and error "password should have at least 8 characters" is reported

  Scenario: creation fails with username not only consisting of letters and valid password
    Given new user is selected
    When incorrect username "kalerv0" and valid password "kalervootti" are given
    Then user is not created and error "username can not contain only letters" is reported

  Scenario: creation fails with correct username and password consisting of letters
    Given new user is selected
    When correct username "kalervo" and invalid password "kalervootti" are given
    Then user is not created and error "password can not contain only letters" is reported

  Scenario: creation fails with already taken username and valid pasword
    Given new user is selected
    When correct username "kalervo" and valid password "k4l3rv00tt1" are given
    Then user is not created and error "username is already taken" is reported

  Scenario: creation fails when password and password confirmation do not match
    Given new user is selected
    When correct username "kalervo" and valid password "k4l3rv00tt1" and invalid passwordConfirmation "kal3rv00tti" are given
    Then user is not created and error "password and password confirmation do not match" is reported

  Scenario: user can login with successfully generated account
    Given user with username "liisa" and password "salainen1" is successfully created
    And login is selected
    When correct username "liisa" and password "salainen1" are given
    Then user is logged in

  Scenario: user can not login with account that is not successfully created
    Given user with username "aa" and password "bad" is unsuccessfully created
    And login is selected
    When incorrect username "aa" and incorrect password "aa" are given
    Then user is not logged in and error message is given
