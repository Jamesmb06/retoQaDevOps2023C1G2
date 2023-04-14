#encoding:UTF-8
Feature: Crear post
  AS  usuario de API de jsonplaceholder
  I WANT TO
  crear post
  SO THAT
  I registar post en el sistema

  @Post
  Scenario Outline: crear post
    Given el usuario esta en la pagina para crear post
    When cuando el usuario envia solicitud con userId <userId> titulo <title> y post  <body>
    Then la pagina retornara un estatus con codigo <codigo> y con userId <userId> titulo <title> y post  <body>
    Examples:
      | userId | title      | body               | codigo |
      | "1"    | "Post1"    | "mi Post1"         | 201    |
      | "4"    | "Historia" | "Historia de post" | 201    |
      | "8"    | "Diseño"   | "Diseño de post"   | 201    |
      | "5"    | "Colores"  | "Colores"          | 201    |