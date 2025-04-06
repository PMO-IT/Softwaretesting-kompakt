Feature: Teste das Anlegen von Fahrzeugen in der Autoverwaltung

  Background: 
    Given der Browser wird mit der URL "http://localhost:8080/softwaretesting/" geöffnet
    And der Titel der URL lautet "Fahrzeug verwaltung - Fahrzeug anlegen"

  Scenario: Lege einen blauen Tesla Model 3 erfolgreich an
    When ein blauer Tesla Model 3 angelegt wird
    And der submit button geklickt wird
    Then wird das Fahrzeug angelegt
    And eine Erfolgsmeldung erscheint

  Scenario: Lege einen roten VW Tiguan erfolgreich an
    When ein roter VW Tiguan angelegt wird
    And der submit button geklickt wird
    Then wird das Fahrzeug angelegt
    And eine Erfolgsmeldung erscheint

  Scenario: Lege ein neues Fahrzeug erfolgreich an
    When ein neues Fahrzeug angelegt wird
      | auto | blau | tesla | model 3 |
    And der submit button geklickt wird
    Then wird das Fahrzeug angelegt
    And eine Erfolgsmeldung erscheint

  Scenario Outline: Lege mehrere neue Fahrzeuge erfolgreich an
    When ein neues Fahrzeug "<fahrzeugart>" der Marke "<marke>" angelegt wird, mit der Farbe "<farbe>" und dem Model "<model>"
    And der submit button geklickt wird
    Then wird das Fahrzeug angelegt
    And eine Erfolgsmeldung erscheint

    Examples: 
      | fahrzeugart | farbe   | marke | model  |
      | auto        | blau    | tesla | model3 |
      | motorrad    | schwarz | bmw   | X3     |

  Scenario Outline: Lege mehrere neue Fahrzeuge mit Datentabelle erfolgreich an
    When ein neues Fahrzeug angelegt wird
      | fahrzeug      | farbe   | marke   | model   |
      | <fahrzeugart> | <farbe> | <marke> | <model> |
    And der submit button geklickt wird
    Then wird das Fahrzeug angelegt
    And eine Erfolgsmeldung erscheint

    Examples: 
      | fahrzeugart | farbe   | marke | model  |
      | auto        | blau    | tesla | model3 |
      | motorrad    | schwarz | bmw   | X3     |
