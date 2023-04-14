Feature: Convertir diferentes cantidades y divisas
  Como: Un usuario de los servicios de Xignite
  Quiero: Convertir diferentes divisas
  De manera que

  @ConvertValue
  Scenario Outline: Convertir divisas
    Given Tengo acceso al servidor de la API de Xignite
    When Trato de convertir de la divisa '<from>', a la divisa '<to>' la cantidad de '<amount>'
    Then Se vera un codigo de respuesta <code>
    And Vere el resultado '<result>'
    Examples:
      | from | to  | amount | code | result |
      | COP  | USD | 50000  | 200  | 11.2   |
      | EUR  | USD | 20.5   | 200  | 22.6   |
      | CLP  | CAD | 30000  | 200  | 50.4   |