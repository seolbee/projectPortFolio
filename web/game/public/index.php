<?php

use App\Route;

session_start();

define("__ROOT" , dirname(__DIR__));

require (__ROOT . "/autoload.php");
require (__ROOT . "/web.php");

$url = isset($_GET['url']) ? "/" . $_GET['url'] : "/";

Route::route($url);