<?php

use App\Route;

Route::get("/", "MainController@index");
Route::get("/rank", "MainController@rank");
Route::get("/ranking", "MainController@ranking");

if(isset($_SESSION['user'])){
    Route::get("/game", "MainController@game");
    Route::get("/board/logout", "BoardController@logout");
    Route::post("/board/rank", "BoardController@rank");
} else{
    Route::get("/login", "MainController@login");
    Route::get("/register", "MainController@register");
    Route::post("/board/login", "BoardController@login");
    Route::post("/board/register", "BoardController@register");
}

Route::get("/error", "MainController@error");