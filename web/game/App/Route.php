<?php

namespace App;

class Route
{
    public static $GET=[];
    public static $POST=[];

    public static function route($url)
    {
        foreach(self::${$_SERVER['REQUEST_METHOD']} as $req){
            if($req[0] === $url){
                $actions = explode("@", $req[1]);
                $cName = "\\App\\Controller\\" . $actions[0];
                $cInstance = new $cName();
                $cInstance->{$actions[1]}();
                return;
            }
        }
        echo "<script>";
        echo "alert('로그인한 유저들만 사용 할 수 있습니다. 로그인 사이트로 이동합니다.');";
        echo "location.href='/login';";
        echo "</script>";
        exit;
    }

    public static function get($link, $action)
    {
        self::$GET[] = [$link, $action];
    }

    public static function post($link, $action)
    {
        self::$POST[] = [$link, $action];
    }
}