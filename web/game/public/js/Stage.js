class Stage{
    constructor(gw, gh, imgs){
        this.stage = [
            [
                {
                    time : 2,
                    data : {
                        x : gw / 3 - 30,
                        y : -60,
                        w : 60,
                        h : 40,
                        imgs : imgs.enemy1,
                        s : 120,
                        v: [
                            {
                                time : 2,
                                v : new Vector(0, 1)
                            },
                            {
                                time : 5,
                                v : new Vector(0, 0)
                            },
                            {
                                time : 100,
                                v : new Vector(1, 0)
                            }
                        ],
                        b : false,
                        name : 'enemy2',
                        hp : 5
                    }
                },
                {
                    time : 2,
                    data : {
                        x : gw / 3 * 2 - 30,
                        y : -60,
                        w : 60,
                        h : 40,
                        imgs : imgs.enemy1,
                        s : 120,
                        v: [
                            {
                                time : 2,
                                v : new Vector(0, 1)
                            },
                            {
                                time : 5,
                                v : new Vector(0,0)
                            },
                            {
                                time : 100,
                                v : new Vector(-1, 0)
                            }
                        ],
                        b : false,
                        name : 'enemy2',
                        hp : 5
                    }
                },
                {
                    time : 6,
                    data:{
                        x: gw / 5 - 30,
                        y: -120,
                        w: 60,
                        h: 40,
                        imgs:imgs.enemy2,
                        s: 100,
                        v:[
                            {
                                time : 5,
                                v : new Vector(0, 1)
                            },
                            {
                                time : 6,
                                v: new Vector(0,0)
                            },
                            {
                                time : 100,
                                v : new Vector(-1, 0)
                            }
                        ],
                        b:false,
                        hp: 5,
                        name:"enemy1"
                    }
                },
                {
                    time : 6,
                    data:{
                        x: gw / 5 * 2 - 30,
                        y: -40,
                        w: 60,
                        h: 40,
                        imgs:imgs.enemy2,
                        s: 100,
                        v: [
                            {
                                time : 5,
                                v : new Vector(0, 1)
                            },
                            {
                                time : 6,
                                v: new Vector(0,0)
                            },
                            {
                                time : 100,
                                v : new Vector(-1, 0)
                            }
                        ],
                        b:false,
                        hp: 5,
                        name:"enemy1"
                    }
                },
                {
                    time : 6,
                    data:{
                        x: gw / 5 * 3  - 30,
                        y: -40,
                        w: 60,
                        h: 40,
                        imgs:imgs.enemy2,
                        s: 100,
                        v: [
                            {
                                time : 5,
                                v : new Vector(0, 1)
                            },
                            {
                                time : 6,
                                v: new Vector(0,0)
                            },
                            {
                                time : 100,
                                v : new Vector(1, 0)
                            }
                        ],
                        b:false,
                        hp: 5,
                        name:"enemy1"
                    }
                },
                {
                    time : 6,
                    data:{
                        x: gw / 5 * 4 - 30,
                        y: -120,
                        w: 60,
                        h: 40,
                        imgs:imgs.enemy2,
                        s: 100,
                        v: [
                            {
                                time : 5,
                                v : new Vector(0, 1)
                            },
                            {
                                time : 6,
                                v: new Vector(0,0)
                            },
                            {
                                time : 100,
                                v : new Vector(1, 0)
                            }
                        ],
                        b:false,
                        hp: 5,
                        name:"enemy1"
                    }
                },
                {
                    time : 6,
                    data:{
                        x: -60,
                        y: -40,
                        w: 60,
                        h: 40,
                        imgs:imgs.enemy2,
                        s: 100,
                        v: [
                            {
                                time : 2,
                                v : new Vector(1, 1)
                            },
                            {
                                time : 5,
                                v : new Vector(0,0)
                            },
                            {
                                time : 2,
                                v : new Vector(-1,1)
                            },
                            {
                                time : 100,
                                v : new Vector(1, 1)
                            }
                        ],
                        b:false,
                        hp: 5,
                        name:"enemy1"
                    }
                },
                {
                    time : 7,
                    data:{
                        x: gw+60,
                        y: -40,
                        w: 60,
                        h: 40,
                        imgs:imgs.enemy2,
                        s: 100,
                        v: [
                            {
                                time : 2,
                                v : new Vector(-1, 1)
                            },
                            {
                                time : 5,
                                v : new Vector(0,0)
                            },
                            {
                                time : 2,
                                v : new Vector(1,1)
                            },
                            {
                                time : 100,
                                v : new Vector(-1, 1)
                            }
                        ],
                        b:false,
                        hp: 5,
                        name:"enemy1"
                    }
                },
                {
                    time : 7,
                    data:{
                        x: -120,
                        y: -80,
                        w: 60,
                        h: 40,
                        imgs:imgs.enemy2,
                        s: 100,
                        v: [
                            {
                                time : 2,
                                v : new Vector(1, 1)
                            },
                            {
                                time : 5,
                                v : new Vector(0,0)
                            },
                            {
                                time : 2,
                                v : new Vector(-1,1)
                            },
                            {
                                time : 100,
                                v : new Vector(1, 1)
                            }
                        ],
                        b:false,
                        hp: 5,
                        name:"enemy1"
                    }
                },
                {
                    time : 7,
                    data:{
                        x: gw+120,
                        y: -80,
                        w: 60,
                        h: 40,
                        imgs:imgs.enemy2,
                        s: 100,
                        v: [
                            {
                                time : 2,
                                v : new Vector(-1, 1)
                            },
                            {
                                time : 5,
                                v : new Vector(0,0)
                            },
                            {
                                time : 2,
                                v : new Vector(1,1)
                            },
                            {
                                time : 100,
                                v : new Vector(-1, 1)
                            }
                        ],
                        b:false,
                        hp: 5,
                        name:"enemy1"
                    }
                },
                {
                    time: 15,
                    data:{
                        x:gw / 2 - 60,
                        y: -100,
                        w: 120,
                        h: 100,
                        imgs:imgs.enemy3,
                        s: 100,
                        v: [
                            {
                                time : 2,
                                v : new Vector(0, 1)
                            },
                            {
                                time : 7,
                                v : new Vector(0, 0)
                            },
                            {
                                time : 3,
                                v : new Vector(1, 0)
                            },
                            {
                                time : 6,
                                v : new Vector(-1, 0)
                            }
                        ],
                        b:true,
                        hp: 100,
                        name:"Boss1"
                    }
                }
            ],
            [
                {
                    time: 3,
                    data:{
                        x:gw / 3 - 30,
                        y: -60,
                        w: 60,
                        h: 40,
                        imgs:imgs.enemy2,
                        s: 100,
                        v: [
                            {
                                time : 3,
                                v : new Vector(0, 1)
                            },
                            {
                                time : 7,
                                v : new Vector(0,0)
                            },
                            {
                                time : 100,
                                v : new Vector(1, 1)
                            }
                        ],
                        b: false,
                        hp: 10,
                        name:"enemy1",
                        maxX: gw / 2 - 60,
                        maxY: 200
                    }
                },
                {
                    time : 3,
                    data : {
                        x : gw / 3 * 2 - 30,
                        y : -60,
                        w : 60,
                        h : 40,
                        imgs : imgs.enemy2,
                        s : 100,
                        v : [
                            {
                                time : 3,
                                v : new Vector(0, 1)
                            },
                            {
                                time : 7,
                                v : new Vector(0,0)
                            },
                            {
                                time : 100,
                                v : new Vector(1, 1)
                            }
                        ],
                        b : false,
                        hp : 10,
                        name : "enemy1"
                    }
                }, 
                {
                    time : 6,
                    data : {
                        x : -50,
                        y : -50,
                        w : 50,
                        h : 50,
                        imgs : imgs.enemy4,
                        s : 120,
                        v : [
                            {
                                time : 3,
                                v : new Vector(1, 1)
                            },
                            {
                                time : 8,
                                v : new Vector(0,0)
                            },
                            {
                                time : 10,
                                v : new Vector(-1, 0)
                            },
                            {
                                time : 100,
                                v : new Vector(1, 1)
                            }
                        ],
                        b : false,
                        hp : 15,
                        name : "enemy2"
                    }
                },
                {
                    time : 6,
                    data : {
                        x : -100,
                        y : -100,
                        w : 50,
                        h : 50,
                        imgs : imgs.enemy4,
                        s : 120,
                        v : [
                            {
                                time : 3,
                                v : new Vector(1, 1)
                            },
                            {
                                time : 8,
                                v : new Vector(0,0)
                            },
                            {
                                time : 10,
                                v : new Vector(-1, 0)
                            },
                            {
                                time : 100,
                                v : new Vector(1, 1)
                            }
                        ],
                        b : false,
                        hp : 15,
                        name : "enemy2"
                    }
                },
                {
                    time : 6,
                    data : {
                        x : -150,
                        y : -150,
                        w : 50,
                        h : 50,
                        imgs : imgs.enemy4,
                        s : 120,
                        v : [
                            {
                                time : 3,
                                v : new Vector(1, 1)
                            },
                            {
                                time : 8,
                                v : new Vector(0,0)
                            },
                            {
                                time : 10,
                                v : new Vector(-1, 0)
                            },
                            {
                                time : 100,
                                v : new Vector(1, 1)
                            }
                        ],
                        b : false,
                        hp : 15,
                        name : "enemy2"
                    }
                },
                {
                    time : 6,
                    data : {
                        x : -200,
                        y : -200,
                        w : 50,
                        h : 50,
                        imgs : imgs.enemy4,
                        s : 120,
                        v : [
                            {
                                time : 3,
                                v : new Vector(1, 1)
                            },
                            {
                                time : 8,
                                v : new Vector(0,0)
                            },
                            {
                                time : 10,
                                v : new Vector(-1, 0)
                            },
                            {
                                time : 100,
                                v : new Vector(1, 1)
                            }
                        ],
                        b : false,
                        hp : 15,
                        name : "enemy2"
                    }
                },
                {
                    time : 6,
                    data : {
                        x : gw + 50,
                        y : -50,
                        w : 50,
                        h : 50,
                        imgs : imgs.enemy4,
                        s : 120,
                        v : [
                            {
                                time : 3,
                                v : new Vector(-1, 1)
                            },
                            {
                                time : 8,
                                v : new Vector(0,0)
                            },
                            {
                                time : 10,
                                v : new Vector(1, 0)
                            },
                            {
                                time : 100,
                                v : new Vector(-1, 1)
                            }
                        ],
                        b : false,
                        hp : 15,
                        name : "enemy2"
                    }
                },
                {
                    time : 6,
                    data : {
                        x : gw + 100,
                        y : -100,
                        w : 50,
                        h : 50,
                        imgs : imgs.enemy4,
                        s : 120,
                        v : [
                            {
                                time : 3,
                                v : new Vector(-1, 1)
                            },
                            {
                                time : 8,
                                v : new Vector(0,0)
                            },
                            {
                                time : 10,
                                v : new Vector(1, 0)
                            },
                            {
                                time : 100,
                                v : new Vector(-1, 1)
                            }
                        ],
                        b : false,
                        hp : 15,
                        name : "enemy2"
                    }
                },
                {
                    time : 6,
                    data : {
                        x : gw + 150,
                        y : -150,
                        w : 50,
                        h : 50,
                        imgs : imgs.enemy4,
                        s : 120,
                        v : [
                            {
                                time : 3,
                                v : new Vector(-1, 1)
                            },
                            {
                                time : 8,
                                v : new Vector(0,0)
                            },
                            {
                                time : 10,
                                v : new Vector(1, 0)
                            },
                            {
                                time : 100,
                                v : new Vector(-1, 1)
                            }
                        ],
                        b : false,
                        hp : 15,
                        name : "enemy2"
                    }
                },
                {
                    time : 12,
                    data : {
                        x : gw / 6 - 30,
                        y : -100,
                        w : 50,
                        h : 50,
                        imgs : imgs.enemy5,
                        s : 250,
                        v : [
                            {
                                time : 1,
                                v : new Vector(0, 1)
                            },
                            {
                                time : 5,
                                v : new Vector(0, 0)
                            },
                            {
                                time : 100,
                                v : new Vector(2, 1)
                            }
                        ],
                        b : false,
                        hp : 20,
                        name : "enemy3"
                    }
                },
                {
                    time : 12,
                    data : {
                        x : gw / 6 * 2 - 30,
                        y : -100,
                        w : 50,
                        h : 50,
                        imgs : imgs.enemy5,
                        s : 250,
                        v : [
                            {
                                time : 1,
                                v : new Vector(0, 1)
                            },
                            {
                                time : 5,
                                v : new Vector(0, 0)
                            },
                            {
                                time : 100,
                                v : new Vector(1, 1)
                            }
                        ],
                        b : false,
                        hp : 20,
                        name :"enemy3"
                    }
                },
                {
                    time : 12,
                    data : {
                        x : gw / 6 * 3- 30,
                        y : -100,
                        w : 50,
                        h : 50,
                        imgs : imgs.enemy5,
                        s : 250,
                        v : [
                            {
                                time : 1,
                                v : new Vector(0, 1)
                            },
                            {
                                time : 5,
                                v : new Vector(0, 0)
                            },
                            {
                                time : 100,
                                v : new Vector(0, 1)
                            }
                        ],
                        b : false,
                        hp : 20,
                        name :"enemy3"
                    }
                },
                {
                    time : 12,
                    data : {
                        x : gw / 6 * 4- 30,
                        y : -100,
                        w : 50,
                        h : 50,
                        imgs : imgs.enemy5,
                        s : 250,
                        v : [
                            {
                                time : 1,
                                v : new Vector(0, 1)
                            },
                            {
                                time : 5,
                                v : new Vector(0, 0)
                            },
                            {
                                time : 100,
                                v : new Vector(-1, 1)
                            }
                        ],
                        b : false,
                        hp : 20,
                        name :"enemy3"
                    }
                },
                {
                    time : 12,
                    data : {
                        x : gw / 6 * 5- 30,
                        y : -100,
                        w : 50,
                        h : 50,
                        imgs : imgs.enemy5,
                        s : 250,
                        v : [
                            {
                                time : 1,
                                v : new Vector(0, 1)
                            },
                            {
                                time : 5,
                                v : new Vector(0, 0)
                            },
                            {
                                time : 100,
                                v : new Vector(-2, 1)
                            }
                        ],
                        b : false,
                        hp : 20,
                        name :"enemy3"
                    }
                },
                {
                    time : 20,
                    data : {
                        x : gw / 2 - 90,
                        y : -250,
                        w : 180,
                        h : 250,
                        imgs : imgs.enemy6,
                        s : 100,
                        v : [
                            {
                                time : 2,
                                v : new Vector(0, 1)
                            },
                            {
                                time : 5,
                                v : new Vector(0, 0)
                            },
                            {
                                time : 7,
                                v : new Vector(1, 0)
                            },
                            {
                                time : 10,
                                v : new Vector(0, 1)
                            },
                            {
                                time : 14,
                                v : new Vector(-1, 0)
                            },
                            {
                                time : 17,
                                v : new Vector(0, -1)
                            }
                        ],
                        b: true,
                        hp : 200,
                        name :"boss2"
                    }
                }
            ]
        ]

        this.itemData = [
            {
                name : "speed",
                skill : function skill(player){
                    if(!this.active) return;
                    if(player.speed >= 500) return;
                    player.speed += 50;
                    player.bulletSpeed += 20;
                },
                img : imgs.fuel,
                w : 40,
                h : 50
            },
            {
                name : "bullet",
                skill : function skill(player){
                    if(!this.active) return;
                    if(player.bulletLength >= player.bulletData.length) return;
                    player.bulletLength += 2;
                },
                img : imgs.bullet_item,
                w : 20,
                h : 40
            },
            {
                name : "shield",
                skill: function skill(player){
                    if(!this.active) return;
                    player.shield++;
                },
                img: imgs.shield,
                w : 30,
                h : 40,
            },
            {
                name : "heart",
                skill : function skill(player){
                    if(!this.active) return;
                    player.hp++;
                },
                img: imgs.heart,
                w : 20,
                h : 20
            }
        ]
    }
}