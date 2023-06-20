# Heroes4U - Heroes for you!!!
A superheroes app using Ktor Server, MVVM + Clean Architecture, Jetpack Compose, Coroutines, Flow, Room Database, Paging, Dagger-Hilt, and support Dark Theme

<p>
  <a href="https://github.com/namnpse/superheroes-app">
    <img src="https://img.shields.io/github/stars/namnpse/superheroes-app?logo=github" />
  </a>
  <img src="https://img.shields.io/github/license/namnpse/superheroes-app?logo=github" />
  <img src="https://img.shields.io/badge/compose-1.3.1-blue.svg" />
  <img src="https://img.shields.io/badge/java-11-blue.svg" />
  <img src="https://img.shields.io/badge/kotlin-1.7.10-blue.svg" />
  <img src="https://img.shields.io/badge/ktor-v1.6.4-blue.svg" />
  <a href="https://github.com/namnpse">
    <img alt="GitHub: Namnpse" src="https://img.shields.io/github/followers/namnpse?label=Follow&style=social" target="_blank" />
  </a>
</p>

Made by Nguyen Phuong Nam (namnpse)<br>
Check out the full source code [here](https://github.com/namnpse/superheroes-app/)

## Features

• Login via email<br>
• Display list heroes<br>
• View heroes info: name, bio, power, original, author, etc...<br>
• Login to add favorite heroes to your list<br>

## Screenshots

#### Light Theme

![Light Theme](https://github.com/namnpse/superheroes-app/blob/dev/screenshots/heroes4u-light-theme.png)

#### Dark Theme

![Dark Theme](https://github.com/namnpse/superheroes-app/blob/dev/screenshots/heroes4u-dark-theme.png)

## Project Structure:
#### Back End: Ktor 1.6.4
<!-- 1. First ordered list item
2. Another item
⋅⋅* Unordered sub-list. 
1. Actual numbers don't matter, just that it's a number
⋅⋅1. Ordered sub-list
4. And another item.

⋅⋅⋅You can have properly indented paragraphs within list items. Notice the blank line above, and the leading spaces (at least one, but we'll use three here to also align the raw Markdown).

⋅⋅⋅To have a line break without a paragraph, you will need to use two trailing spaces.⋅⋅
⋅⋅⋅Note that this line is separate, but within the same paragraph.⋅⋅
⋅⋅⋅(This is contrary to the typical GFM line break behaviour, where trailing spaces are not required.)

*^ Unordered list can use asterisks
- Or minuses
+ Or pluses
+ Or pluses -->
* ## Tech Stack
  * Ktor Server
  * Koin
  * MVVM + Clean Architecture
  * Jetpack Compose
  * Coroutines, Flow, 
  * Room Database, 
  * Paging, 
  * Dagger-Hilt
  * Dark Theme
  * Firebase
* #### DI
  * Koin
* #### Models
  * Response
  * Hero
* #### Plugins
  * Default Header
  * Monitoring
  * Routing
  * Serialization
  * Status Page
* #### Repository
  * HeroRepository
  * HeroRepositoryImpl
* #### Routes
  * Root
  * HeroRoute
  * SearchRoute

#### Authentication: Firebase

#### Android App: Kotlin + Compose

* #### DI
  * Dagger-Hilt
* #### Data
  * Local
  * Paging Source
  * Remote
  * Repository
* #### Domain
  * Models
  * Repository
  * Use cases
* #### Presentation
  * Common
  * Components
  * Constants
  * Image Slider
  * Screens
* #### Navigation
  * NavGraph
  * Screens
* #### Theme
  * Color
  * Dimens
  * Font
  * Shape
  * Theme
  * ThemeState
  * Type
* #### Util
  * Constants
  * Palette Generator
  * Utils

## License

MIT License, see the [LICENSE.md](https://github.com/namnpse/superheroes-app/blob/master/LICENSE) file for details.
