Feature: Conversion de divisas con CurrencySystem
  Yo como usuario del servicio CurrencySystem
  quiero realizar un cambio de divisas
  para saber su equivalente en otras monedas

  Scenario Outline: Conversion exitosa de una divisa a otra
    Given que tengo acceso al servicio web de CurrencySystem para la conversion de divisas
    When envio <cantidad> <divisa_origen> a <divisa_destino> al servicio
    Then deberia recibir el resultado de la conversion no nulo <resultado>

    Examples:
      | cantidad | divisa_origen | divisa_destino | resultado            |
      | 10       | "USD"         | "EUR"          | "9.0785292782569229" |
      | 10000    | "COP"         | "USD"          | "2.2499601661110629" |
      | 5        | "CAD"         | "AUD"          | "5.5366217223389125" |

