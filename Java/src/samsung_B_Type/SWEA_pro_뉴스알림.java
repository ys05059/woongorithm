package samsung_B_Type;


/**
 * 뉴스 알림 삭제 - 뉴스 삭제시 유저가 가지고 있는 해당 뉴스 알림 삭제
 * 
 * 시간순으로 정렬이 필요함 ->checkUser때문에
 * 마지막에 최신 3개 -> 시간순으로 정렬필요 -> PQ or TreeSet
 * 
 * 첫 시도
 * 어떤 자료구조 쓸래?
 * 1. 뉴스채널에 등록한 유저정보 저장 - ArrayList[뉴스채널수 K]
 * 2. 각 채널별 뉴스 저장 - HashMap<뉴스채널ID,TreeSet<뉴스ID>>
 * - offerNews를 통해 들어온 뉴스 저장
 * - 뉴스 삭제를 위해 필요 - value 전체 순회
 * 3. 시간순으로 유저별 알림 대기열 (마지막에 정렬해도됨) -> TreeSet[유저수 N]
 * - 삭제도 여기서 해야함
 * - 알림 울리고 나면 삭제해줘야함 -> 하나씩 poll하면 됨
 * - 알림 최대 : 4_000_000 -> 삽입,삭제,조회 log(4_000_000) -> 6.xx
 * 
 * 버그 -> 알림이 추가되어 있지않음. offer할 때마다 채널에 대한 user수는 맞는거보면 삭제는 잘 된 것 같은데 => 해결은 했음
 *
 *
 * checkUser : 1_000 * 30_000 * (log(30_000) + log(30_000))-> O(270_000_000_->2.7초
 * - 각 유저 최대 알림 수는 30_000이다
 * - 삭제 체크 : log(30_000)
 * - temp에 알림 다시 넣어두는 경우도 있음 log(30_000)
 * 
 * 삭제 로직을 여기서 체크하니까 결국 알림 전체 돌아야해서 터짐..
 * 그럼 삭제를 진짜 어떻게 해야하지? 그냥 3개만 뽑고 끝나는거면 괜찮은데 결국 받은 알림 개수를 반환해줘야함..(삭제처리가 되어있어야했다)
 * 그냥 size로 받을려면 삭제처리를 미리 해둬서 set의 크기를 맞춰둬야함.
 * 
 * 결국 잘못했던 것은 무엇인가? => checkUser 반환값 못보고 이런식으로 구현했음..
 * 
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

class UserSolution_뉴스알림 {
  private static Set<Integer> allChannels;
  private static Map<Integer, News> allNews;
  private static Map<Integer, Integer> checks;
  private static Map<Integer, TreeSet<User>> usersOfChannel; // HashMap<뉴스채널ID, UserId> 최대 K
  private static Map<Integer, TreeSet<News>> newsOfChannel; // HashMap<뉴스채널ID,TreeSet<뉴스ID>> 최대 K

  void init(int N, int K) {
    allChannels = new HashSet<>();
    allNews = new HashMap<>();
    checks = new HashMap<>();
    usersOfChannel = new HashMap<>();
    newsOfChannel = new HashMap<>();

  }

  /**
   * registerUser : 5_000 * log(500) = O(13,500)
   */
  // mTime에 들어온 유저는 mTime 뉴스 알림은 못받는다
  void registerUser(int mTime, int mUID, int mNum, int mChannelIDs[]) {
    // 각 채널에 유저 등록
    for (int i = 0; i < mNum; i++) {
      int cId = mChannelIDs[i];
      allChannels.add(cId);
      if (!usersOfChannel.containsKey(cId)) {
        usersOfChannel.put(cId, new TreeSet<>());
      }
      usersOfChannel.get(cId).add(new User(mUID, mTime, cId));

    }
  }

  /**
   * offerNews : 30_000 호출 * log(30_000) = O(120_000)
   */
  int offerNews(int mTime, int mNewsID, int mDelay, int mChannelID) {
    // 뉴스 등록
    if (!newsOfChannel.containsKey(mChannelID)) {
      newsOfChannel.put(mChannelID, new TreeSet<>());
    }
    News curr = new News(mNewsID, mTime, mDelay);
    newsOfChannel.get(mChannelID).add(curr);
    allNews.put(mNewsID, curr);

    return usersOfChannel.get(mChannelID).size();
  }

  /**
   * -cancelNews : 3_000 호출 * 500 * log(30_000) -> O(6,700,000)
   * - 채널 찾기 : O(500)
   * - 해당 채널의 모든 뉴스 탐색해서 삭제 -> log(30_000)
   */
  void cancelNews(int mTime, int mNewsID) {
    // 채널 찾기
    // System.out.println("channels: " + Arrays.toString(channels.toArray()));
    for (int cId : allChannels) {
      if (newsOfChannel.containsKey(cId)) {
        TreeSet<News> news = newsOfChannel.get(cId);

        // 뉴스 삭제 - mTime이랑 관련이 없다. 그냥 news자체를 삭제하고 나중에 처리 -> 이방법 안됨
        News temp = allNews.get(mNewsID);
        if (news.contains(temp)) {
          news.remove(temp);
        }
      }
    }
  }

  /**
   * checkUser : 1_000 호출 * 500 * log(500) * log(30_000) * 6 -> 36,250,847 -> 0.36초
   * - 모든 채널 중에 해당 유저가 구독하고 있는 채널 찾기 O(500 * log(500))
   * - 해당 채널에 있는 뉴스 중 범위에 맞는 뉴스 알림 찾기 O(log(30_000))
   * - 알람 추가하고 뽑기 O(log(30_000)*5)
   */
  int checkUser(int mTime, int mUID, int mRetIDs[]) {

    // 모든 채널에 있는 뉴스 탐색
    // 유저가 구독한 채널 각각에 대해 채널에 offer된 뉴스 탐색
    // System.out.println(mTime + ", " + mUID + ", ");
    TreeSet<Notice> notices = new TreeSet<>();
    for (int cId : allChannels) {
      TreeSet<User> channel = usersOfChannel.get(cId);
      User me = new User(mUID, 0, 0);
      if (!channel.contains(me)) continue;
      TreeSet<News> news = newsOfChannel.get(cId);
      if (news == null) continue;
      me = channel.floor(me); // me 제대로 찾기

      // 만약 이미 알림을 보냈다면 날려줘야함
      int prevTime = me.joinTime;
      if (checks.containsKey(mUID)) prevTime = Math.max(prevTime, checks.get(mUID));
      news = (TreeSet<News>) news.subSet(new News(0, prevTime + 1, 0), new News(0, mTime + 1, 0));
      for (News next : news) {
        notices.add(new Notice(next.offerTime + next.delayTime, next.id, cId));
      }
    }
    int ans = notices.size();
    for (int i = 0; i < 3; i++) {
      if (notices.isEmpty()) break;
      mRetIDs[i] = notices.pollFirst().newsId;
    }
    checks.put(mUID, mTime);
    return ans;
  }

  private static class User implements Comparable<User> {
    int id;
    int joinTime;
    int channelId;

    public User(int id, int joinTime, int channelId) {
      this.id = id;
      this.joinTime = joinTime;
      this.channelId = channelId;
    }


    @Override
    public String toString() {
      return "User [id=" + id + ", joinTime=" + joinTime + ", channelId=" + channelId + "]";
    }


    @Override
    public int compareTo(User o) {
      return this.id - o.id;
    }

  }

  private static class News implements Comparable<News> {
    int id;
    int offerTime;
    int delayTime;

    public News(int id, int offerTime, int delayTime) {
      this.id = id;
      this.offerTime = offerTime;
      this.delayTime = delayTime;
    }

    @Override
    public int compareTo(News o) {
      if ((this.offerTime + this.delayTime) == (o.offerTime + o.delayTime)) {
        return this.id - o.id;
      }
      return (this.offerTime + this.delayTime) - (o.offerTime + o.delayTime);
    }

    @Override
    public String toString() {
      return "News [id=" + id + ", offerTime=" + offerTime + ", delayTime=" + delayTime + "]";
    }

  }

  private static class Notice implements Comparable<Notice> {
    int time;
    int newsId;
    int channelId;

    public Notice(int time, int newsId, int channelId) {
      this.time = time;
      this.newsId = newsId;
      this.channelId = channelId;
    }

    @Override
    public int compareTo(Notice o) {
      if (this.time == o.time) {
        return o.newsId - this.newsId;
      }
      return o.time - this.time;
    }

    @Override
    public String toString() {
      return "Notice [time=" + time + ", newsId=" + newsId + ", channelId=" + channelId + "]";
    }

  }

  void printNewsOfChannel() {
    for (int cId : allChannels) {
      if (newsOfChannel.containsKey(cId)) {
        TreeSet<News> news = newsOfChannel.get(cId);
        System.out.println("cId : " + cId + " -> " + Arrays.toString(news.toArray()));
      }
    }
  }


}


class SWEA_pro_뉴스알림 {
  private static BufferedReader br;
  private static UserSolution_뉴스알림 usersolution = new UserSolution_뉴스알림();

  private final static int INIT = 0;
  private final static int REGI = 1;
  private final static int OFFER = 2;
  private final static int CANCEL = 3;
  private final static int CHECK = 4;

  private static int gids[] = new int[30];
  private static int ansids[] = new int[3];
  private static int retids[] = new int[3];

  private static boolean run() throws Exception {

    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    int N, K, cmd, ans, ret;
    int time, num, uid, gid, nid, delay;

    int Q = Integer.parseInt(st.nextToken());
    boolean ok = false;

    for (int i = 0; i < Q; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      cmd = Integer.parseInt(st.nextToken());

      if (cmd == INIT) {
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        usersolution.init(N, K);
        ok = true;
      } else if (cmd == REGI) {
        time = Integer.parseInt(st.nextToken());
        uid = Integer.parseInt(st.nextToken());
        num = Integer.parseInt(st.nextToken());
        for (int m = 0; m < num; m++) {
          gids[m] = Integer.parseInt(st.nextToken());
        }

        usersolution.registerUser(time, uid, num, gids);
      } else if (cmd == OFFER) {
        time = Integer.parseInt(st.nextToken());
        nid = Integer.parseInt(st.nextToken());
        delay = Integer.parseInt(st.nextToken());
        gid = Integer.parseInt(st.nextToken());
        ans = Integer.parseInt(st.nextToken());

        ret = usersolution.offerNews(time, nid, delay, gid);
        if (ans != ret) {
          ok = false;
          System.err.println("offer 틀렸습니다 ans : " + ans + " ret : " + ret);
        } else {
          // System.out.println("offer 정답입니다.");
        }
      } else if (cmd == CANCEL) {
        time = Integer.parseInt(st.nextToken());
        nid = Integer.parseInt(st.nextToken());

        usersolution.cancelNews(time, nid);
        // System.out.println("cancel 수행함");
      } else if (cmd == CHECK) {
        time = Integer.parseInt(st.nextToken());
        uid = Integer.parseInt(st.nextToken());

        ret = usersolution.checkUser(time, uid, retids);

        ans = Integer.parseInt(st.nextToken());
        num = ans;
        if (num > 3) num = 3;
        for (int m = 0; m < num; m++) {
          ansids[m] = Integer.parseInt(st.nextToken());
        }
        if (ans != ret) {
          ok = false;
          System.err.println("check 틀렸습니다 ans : " + ans + " ret : " + ret);
          System.err.println("ansids : " + Arrays.toString(ansids));
          System.err.println("retids : " + Arrays.toString(retids));
        } else {
          for (int m = 0; m < num; m++) {
            if (ansids[m] != retids[m]) {
              ok = false;
            }
          }
          if (!ok) {
            System.err.println("ansids : " + Arrays.toString(ansids));
            System.err.println("retids : " + Arrays.toString(retids));
          } else {
            // System.out.println("check 정답입니다.");
          }
        }
      } else ok = false;
    }
    return ok;
  }

  public static void main(String[] args) throws Exception {
    int T, MARK;
    long startTime = System.currentTimeMillis();
    System.setIn(new java.io.FileInputStream("res/input.txt"));
    br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");
    T = Integer.parseInt(stinit.nextToken());
    MARK = Integer.parseInt(stinit.nextToken());

    for (int tc = 1; tc <= T; tc++) {
      int score = run() ? MARK : 0;
      System.out.println("#" + tc + " " + score);
    }
    long endTime = System.currentTimeMillis();
    double executionTime = (endTime - startTime) / 1000.0;
    System.out.println("Execution time: " + executionTime + " seconds");

    br.close();
  }
}
