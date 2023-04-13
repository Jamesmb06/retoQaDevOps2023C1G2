Feature: Convercion de numeros a letras
  AS
  usuario del servicio
  I WANT
  obtener
  SO THAT
  mirar numeros en letras

  @numbersinletters
  Scenario: convertir numeros a letras
    Given el usuario quiere convertir numeros a letras
    When el usuario envia la solicitud al servicio de conversion de numeros api
    Then el usuario obtiene los numeros en letras