<?php

namespace App\Controller;

use App\DB;

class MainController extends MasterController
{
    public function index(){
        $this -> render("main", ['msg'=>"안녕하세요"]);
    }

    public function login(){
        $this -> render("login");
    }

    public function register(){
        $this -> render("register");
    }

    public function game(){
        $this -> render("game");
    }

    public function rank(){
        $sql = "SELECT COUNT(*) AS cnt FROM game_rank";
        $cnt = DB::fetch($sql)->cnt;
        $page = ceil($cnt / 5);
        if(!isset($_GET['p'])){
            $p = 1;
        } else{
            $p = $_GET['p'];
        }
        if($p > $page || !is_numeric($p)){
            $p = 1;
        }
        $sql = "SELECT game_rank.*, game_user.nicname FROM game_rank, game_user WHERE game_rank.user_idx = game_user.idx ORDER BY score DESC, date ASC LIMIT ". ($p - 1) * 5 .  ", 5";
        $data = DB::fetchAll($sql);
        $this-> render("rank", ['data'=> $data, 'page'=> $page, 'currentPage'=>$p]);
    }
    
    public function error(){
        $this->render("error");
    }

    public function ranking(){
        $sql = "SELECT game_rank.*, game_user.nicname FROM game_rank, game_user WHERE game_rank.user_idx = game_user.idx ORDER BY score DESC LIMIT 0 , 10";
        $data = DB::fetchAll($sql);
        echo json_encode(['data'=>$data], JSON_UNESCAPED_UNICODE);
    }
}