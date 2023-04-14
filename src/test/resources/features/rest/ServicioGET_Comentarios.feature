Feature: Probar el servicio GET de la pagina dummyJson
  Como usuario de la pagina dummyjson
  Quiero probar el servicio que muestra los comentarios de los usuarios
  Para verificar que funciona correctamente

  Scenario:  Hacer peticion GET para ver todos los comentarios
    Given que el usuario tiene acceso a los servicios API REST
    When el usuario envia una solicitud GET para ver todos los comentarios
    Then debe recibir un codigo de estado 200
    And la respuesta JSON debe mostrar los comentarios realizados por los usuarios