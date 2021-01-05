<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="fontawesome-free-5.12.0-web/fontawesome-free-5.12.0-web/css/all.min.css">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="js/Web.js"></script>
</head>
<body>
    <header>
        <ul class="nav">
            <li><a href="/">Home</a></li>
            <li><a href="/rank" class="rank_btn">Ranking</a></li>
            <li><a href="/game">Game</a></li>
            <?php if(isset($_SESSION['user'])) : ?>
                <li><a href=""><?=$_SESSION['user']->nicname?></a></li>
                <li><a href="" class="logout">logout</a></li>
                <script>
                    logout();
                </script>
            <?php else : ?>
                <li><a href="/login">Login</a></li>
                <li><a href="/register">Register</a></li>
            <?php endif;?>
        </ul>
    </header>