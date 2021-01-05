<section id="ranking">
        <div class="main">
            <p class="sub_title">Ranking</p>
        </div>
        <div class="rank_contain">
            <div class="rank_box">
                <p>Rank</p>
                <p>Nicname</p>
                <p>Score</p>
                <p>Date</p>
            </div>
            <!-- <div class="rank_box rank">
                <p>1</p>
                <p>user1</p>
                <p>90</p>
                <p>2020-01-08</p>
            </div>
            <div class="rank_box rank">
                <p>1</p>
                <p>user1</p>
                <p>90</p>
                <p>2020-01-08</p>
            </div>
            <div class="rank_box rank">
                <p>1</p>
                <p>user1</p>
                <p>90</p>
                <p>2020-01-08</p>
            </div>
            <div class="rank_box rank">
                <p>1</p>
                <p>user1</p>
                <p>90</p>
                <p>2020-01-08</p>
            </div>
            <div class="rank_box rank">
                <p>1</p>
                <p>user1</p>
                <p>90</p>
                <p>2020-01-08</p>
            </div> -->
            <?php foreach($data['data'] as $idx => $item) :?>
              <div class="rank_box rank">
                <p><?=($data['currentPage'] - 1) * 5 + 1 + $idx?></p>
                <p><?=$item->nicname?></p>
                <p><?=$item->score?></p>
                <p><?=$item->date?></p>
              </div>
            <?php endforeach;?>
        </div>
        <div class="pagenation">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                  <li class="page-item <?=$data['currentPage'] <= 1? "disabled" : "" ?>">
                    <a class="page-link" href="#" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                    </a>
                  </li>
                  <?php for($i = 1; $i <= $data['page']; $i++) : ?>
                    <li class="page-item"><a class="page-link" href="/rank?p=<?=$i?>"><?=$i?></a></li>
                  <?php endfor;?>
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                      <span aria-hidden="true">&raquo;</span>
                    </a>
                  </li>
                </ul>
              </nav>
        </div>
    </section>