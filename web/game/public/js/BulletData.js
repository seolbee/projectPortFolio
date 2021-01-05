class BulletData{
    constructor(x, gw, gh, w){
        this.bulletData = {
            "enemy1" : [
                {
                    x : x + w / 2,
                    vector: new Vector(0, 1)
                }
            ],
            "enemy2" : [
                {
                    x : x,
                    vector : new Vector(-0.2, 1)
                },
                {
                    x : x + w,
                    vector : new Vector(0.2, 1)
                }
            ],
            "Boss1" : [
                {
                    x : x,
                    vector : new Vector(-1, 1)
                },
                {
                    x : x,
                    vector : new Vector(0, 1)
                },
                {
                    x : x,
                    vector : new Vector(1, 1)
                },
                {
                    x : x + w / 2,
                    vector : new Vector(0, 1)
                },
                {
                    x : x + w,
                    vector : new Vector(-1, 1)
                },
                {
                    x : x + w,
                    vector : new Vector(0, 1)
                },
                {
                    x : x + w,
                    vector : new Vector(1, 1)
                }
            ],
            "enemy3" : [
                {
                    x : x + w / 2,
                    vector : new Vector(0,1)
                },
                {
                    x : x + w / 2,
                    vector : new Vector(1,1)
                },
                {
                    x : x + w / 2,
                    vector : new Vector(-1,1)
                }
            ],
            "enemy4" : [
                {
                    x : x,
                    vector : new Vector(-1, 1)
                },
                {
                    x : x + w,
                    vector : new Vector(1, 1)
                }
            ],
            "boss2" : [
                {
                    x : x  + w / 10,
                    vector : new Vector(-1, 1)
                },
                {
                    x : x + w / 10,
                    vector : new Vector(0, 1)
                },
                {
                    x : x + w / 10,
                    vector : new Vector(1, 1)
                },
                {
                    x : x  + w / 10 * 2,
                    vector : new Vector(0, 1)
                },
                {
                    x : x  + w / 10 * 3,
                    vector : new Vector(0, 1)
                },
                {
                    x : x  + w / 10 * 4,
                    vector : new Vector(0, 1)
                },
                {
                    x : x  + w / 10 * 5,
                    vector : new Vector(0, 1)
                },
                {
                    x : x  + w / 10 * 6,
                    vector : new Vector(0, 1)
                },
                {
                    x : x  + w / 10 * 7,
                    vector : new Vector(0, 1)
                },
                {
                    x : x  + w / 10 * 8,
                    vector : new Vector(0, 1)
                },
                {
                    x : x  + w / 10 * 9,
                    vector : new Vector(0, 1)
                },
                {
                    x : x  + w ,
                    vector : new Vector(1, 1)
                },
                {
                    x : x + w,
                    vector : new Vector(-1, 1)
                },
                {
                    x : x + w,
                    vector : new Vector(0, 1)
                }
            ]
        }
    }
}