Feature: Ver tareas TO DO
  Como: El usuario con ID 1 de JSONPlaceholder
  Quiero: Ver el titulo de una tarea
  De manera que

  @TODO
  Scenario Outline: Titulo de una tarea
    Given Tengo acceso al servidor de la API de JSONPlaceholder
    When Reviso mis tareas TO DO con ID <id>
    Then Vere el titulo '<title>' de la tarea
    And Recibire un codigo de estado <code>
    Examples:
      | id | title                              | code |
      | 1  | delectus aut autem                 | 200  |
      | 2  | quis ut nam facilis et officia qui | 200  |
      | 4  | et porro tempora                   | 200  |