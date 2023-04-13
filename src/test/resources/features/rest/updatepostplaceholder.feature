Feature: Actualizando un Post
  Yo como usuario de la pagina jsonplaceholder
  Quiero actualizar un post
  para luego editarlo

  Scenario Outline: actualizando un post existente
    Given estoy en la pagina de actualizacion de jsonplaceholder
    When actualizo la informacion de un post <titulo>, <cuerpo>, <id>
    Then me debe devolver el post actualizado <code>, <titulo>

    Examples:
      | titulo           | cuerpo    | id | code |
      | "cerveza aguila" | "malta"   | 1  | 200  |
      | "cerveza pilsen" | "cebada"  | 2  | 200  |
      | "cerveza club"   | "alcohol" | 3  | 200  |

