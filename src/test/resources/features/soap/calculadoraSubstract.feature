Feature: Calculadora SOAP

  Como usuario del servicio SOAP
  Quiero poder realizar operaciones basicas de la calculadora
  Para poder conocer su resultado

  Scenario Outline: Resta de dos numeros enteros
    Given Dado que tengo acceso al servicio SOAP calculadora
    And Tengo los numeros a restar <num1>, <num2>
    When realizo la solicitud para restar los numeros
    Then obtengo un codigo de estado exitoso "200"
    And El resultado correcto de la resta "<total>"

    Examples:
      | num1 | num2 | total |
      | 10   | 5    | 5     |
      | 8    | 2    | 6     |
      | -3   | 4    | -7    |
