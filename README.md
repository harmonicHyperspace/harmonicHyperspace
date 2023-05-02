# Euphonic Hyperspace
Created by: **Ivan Reyes & Gerardo Solis**

## Table of Contents

1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
1. [Completed User Stories](#Completed-User-Stories)

## Overview

### Description

Introducing Euphonic Hyperspace: a music review app that bridges the gap between music lovers, allowing you to share your favorite songs and albums with friends and the world. This app was born out of the desire to make it easy for people to share music recommendations.

EH offers:
- In-depth song and album reviews: Share your thoughts on individual tracks or entire albums, encouraging people to explore every aspect of an artist's work.
- Venue List: Discover local concert venues within a specified radius, read reviews, find upcoming shows, and access ticket distribution websites.
- Point system: Earn points for reviewing songs, incentivizing users to listen and review individual tracks rather than just entire albums.
- Photo sharing tab: Inspired by Instagram stories, this feature allows users to share 15-second music snippets with accompanying images, appealing to photographers, filmmakers, and music enthusiasts alike.

Join the Hyperspace, a vibrant community where you can connect with others through the universal language of music. Share your passion, grow your knowledge, and embark on new musical adventures.


### App Evaluation

- **Category:** Music & Entertainment
- **Mobile:** Easy to access
- **Story:** Helps users share their opinions on songs/albulms
- **Market:** People interested in listening to new music
- **Habit:** Used regularly
- **Scope:** Building the JukeBox Journeys app will be a bit tough because of some technical tasks like working with APIs and storing data. However, even a simpler version of the app is still exciting to create because it's interactive and fun for users. We have a clear idea of what we want the app to do, which helps us stay focused and work better during the development process.

## Product Spec

### 1. User Features (Required and Optional)

**Required Features**

* login/sign up page
* albums categorized by genres (similar to how Spotify does it)
* when user clicks on the genre, it will bring them to a page with the top 9 albums (3 x 3) 
* user clicks album and general info (name, image, year, genre, rating, length of album)
* user clicks songs and general info (name, image, year, genre, rating)
* look at other reviews
* search function to look for specific song/album

**Stretch Features**

* One tap sign in for creating an account (https://developers.google.com/identity/one-tap/android/get-started)
* rate album before they can see other ratings 
* Settings page (change username/password)
* community thread
* venue list 

### 2. Screen Archetypes

* Login Screen
    * user can login and/or create an account
* Stream
    * users can browse through different genres and find the top 9 albums corresponding to that genre
* Detail 
    * when the album/song is clicked, more info is shown to user
* Creation
    * when user writes review on album/song

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Home screen
* Search genre/albums
* Add a review 
* Profile/Settings

**Flow Navigation** (Screen to Screen)

* Sign Up 
    * Discovery page
    * albulm review
* Login Screen
    * home 
* Stream
    * home (after you finish looking at the album)
    * details to find more info on the specific album 
* Detail 
    * home 
* Creation
    * home

## Wireframes
<img src="(https://imgur.com/a/8xrXFGJ)" width=600>![](https://i.imgur.com/VSOz5I4.jpg)

## License

    Copyright [2023] [Ivan Reyes & Gerardo Solis]

    Licensed under the GNU General Public License, version 3.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        https://www.gnu.org/licenses/gpl-3.0.en.html

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
