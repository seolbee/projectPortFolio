<?php

namespace App\Controller;

use App\DB;

class BoardController
{
    public function login()
    {
        $id = $_POST['id'];
        $password = $_POST['password'];

        $sql = "SELECT * FROM game_user WHERE id = ? AND password = PASSWORD(?)";
        $user = DB::fetch($sql, [$id, $password]);

        if(!$user){
            echo json_encode(['result'=> false, 'msg'=> "없는 유저 입니다.", 'link'=>""], JSON_UNESCAPED_UNICODE);
            exit;
        } else{
            $_SESSION['user'] = $user;
            echo json_encode(['result'=> true, 'msg'=> "로그인 완료. 메인 페이지로 이동합니다.", 'link'=>"\/"], JSON_UNESCAPED_UNICODE);
            exit;
        }
    }

    public function register()
    {
        $id = $_POST['id'];
        $password = $_POST['password'];
        $nicname = $_POST['nicname'];
        
        $sql = "SELECT * FROM game_user WHERE id = ? AND password = PASSWORD(?)";
        $user = DB::fetch($sql, [$id, $password]);
        if($user){
            echo json_encode(['result'=> false, 'msg'=>"이미 있는 유저 입니다. 로그인 페이지로 이동합니다.", 'link'=> "/login"], JSON_UNESCAPED_UNICODE);
            exit;
        }

        $sql = "SELECT * FROM game_user WHERE id = ?";
        $data = DB::fetch($sql, [$id]);
        if($data){
            echo json_encode(['result'=> false, 'msg'=> "이미 있는 아이디 입니다. 다른 아이디로 다시 로그인 해주세요", 'link'=>""], JSON_UNESCAPED_UNICODE);
            exit;
        }

        $sql = "SELECT * FROM game_user WHERE password = PASSWORD(?)";
        $pass = DB::fetch($sql, [$password]);

        if($pass){
            echo json_encode(['result'=> false, 'msg'=> "이미 있는 비밀번호 입니다. 다른 비밀번호로 재가입 해주세요", 'link'=>""], JSON_UNESCAPED_UNICODE);
            exit;
        }

        $sql = "SELECT * FROM game_user WHERE nicname = ?";
        $nic = DB::fetch($sql, [$nicname]);

        if($nic){
            echo json_encode(['result'=> false, 'msg'=>"이미 있는 닉네임 입니다. 다른 닉네임으로 재가입 해주세요", 'link' => ""], JSON_UNESCAPED_UNICODE);
            exit;
        }

        $sql = "INSERT INTO game_user (id, password, nicname) VALUE(?, PASSWORD(?), ?)";
        $cnt = DB::query($sql, [$id, $password, $nicname]);
        if($cnt > 0){
            echo json_encode(['result'=> true, "msg"=> "회원가입 성공, 로그인 페이지로 이동합니다.", 'link'=> "/login"], JSON_UNESCAPED_UNICODE);
            exit;
        } else {
            echo json_encode(['result'=> false, "msg"=> "가입 오류", 'link'=>""], JSON_UNESCAPED_UNICODE);
            exit;
        }
    }

    public function logout()
    {
        if(isset($_SESSION['user'])){
            unset($_SESSION['user']);
            echo json_encode(['result'=> true], JSON_UNESCAPED_UNICODE);
        } else{
            echo json_encode(['result'=> false], JSON_UNESCAPED_UNICODE);
        }
    }

    public function rank()
    {
        $score = $_POST['score'];

        $sql = "SELECT * FROM game_rank WHERE game_rank.user_idx = ?";
        $rank = DB::fetch($sql, [$_SESSION['user']-> idx]);
        var_dump($rank);
        if($rank){
            if($rank->score < $score){
                $sql = "UPDATE game_rank SET score = ?, date = NOW() WHERE user_idx = ?";
                $param = [$score, $_SESSION['user']->idx];
            }else{
                echo json_encode(['result'=> false, 'msg'=> "원래 점수보다 작으므로 갱신하지 않습니다.", 'link'=> ""], JSON_UNESCAPED_UNICODE);
                exit;
            }
        } else{
            $sql = "INSERT INTO game_rank (user_idx, score, date) VALUE(?, ?, NOW())";
            $param = [$_SESSION['user']->idx, $score];
        }

        $cnt = DB::query($sql, $param);
        if($cnt > 0){
            echo json_encode(['result'=> true, 'msg'=> "점수 등록 완료", "link"=>""], JSON_UNESCAPED_UNICODE);
        } else{
            echo json_encode(['result'=>false, 'msg'=> "점수 등록 중 오류", 'link'=>"/error"], JSON_UNESCAPED_UNICODE);
        }
    }
}