package samsung_B_Type;

/**
 * 자료구조
 * 1. 각 학년 + 성별로 총 6개의 TreeSet을 만든다
 * log(20,000) = 14.xx
 * logN으로 해결
 * TreeSet으로 항상 정렬해두면 비효율적이다
 * 그럼 쿼리 날릴때마다 재정렬하는게 맞나? 아닌듯
 * 근데 일단 동적배열임 - 점수,ID로 항상 정렬상태 유지 -> TreeSet
 * 트리셋은 1개 변수가 범위로 주어지면 최악의 경우 전부 순회해야한다 -> O(N) -> 이거 아님
 * 트리셋을 애초에 점수,ID로 정렬해두면 점수로 탐색해서 나오는 값 (floor,ceiling 등)으로 해결 가능
 * 
 * 2. 배열 합치기 - 정렬 - 이분탐색 -> 이거 아님
 * 2-1.학년과 성별 조건을 만족하는 학생 배열 만들기
 * 2-2.정렬 -> 만약 모든 학생의 경우 정렬 O(NlogN) N==20,000 -> 300,000
 * 2-3.이분탐색
 * 이 방법은 만약 값 수정이 일어날 때마다 이 과정들을 전부 해야해서 손해다
 *
 * 
 * 그냥 트리셋 쓰면 정렬된 상태이므로
 * 점수가 mScore인 학생 이분탐색해서 만약 안나오면 0
 * 트리셋이기에 insert,delete도 logN이면 해결됨
 * 결국 6TreeSet풀이
 * 
 * remove할 때 mId로 트리셋 탐색해야하는데 이때 O(N)이 걸림
 * 이를 해결해기 위해 HashMap 함께 사용
 */

/**
 * 시간 계산해보기
 * add 호출 20,000
 * 모든 함수 호출 80,000
 * 쿼리만 60,000 가능
 * 100,00 / 6 = 1600
 * 쿼리 1번에 O(1600)안으로 해결
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeSet;



class UserSolution_성적조회 {

  private static final int M1 = 0;
  private static final int F1 = 1;
  private static final int M2 = 2;
  private static final int F2 = 3;
  private static final int M3 = 4;
  private static final int F3 = 5;
  private static final int GroupCnt = 6;

  private TreeSet<Student>[] ts;
  private HashMap<Integer, Student> map;

  public void init() {
    ts = new TreeSet[GroupCnt];
    map = new HashMap<>();
    for (int i = 0; i < GroupCnt; i++) {
      ts[i] = new TreeSet<>();
    }
    return;
  }

  // mGrade학년 mGender인 학생 중에서 점수가 가장 높은 학생의 ID를 반환한다.
  // 점수가 가장 높은 학생이 여러 명이라면, 그 중에서 ID가 가장 큰 값을 반환한다.
  public int add(int mId, int mGrade, char mGender[], int mScore) {
    int group = toGroup(mGrade, mGender);
    Student s = new Student(mId, mScore, group);
    ts[group].add(s);
    map.put(mId, s);
    return ts[group].last().id;
  }

  public int remove(int mId) {
    Student s = map.get(mId);
    System.out.println("mId : " + mId + " student : " + s);
    if (s == null) return 0;
    int group = s.group;
    ts[group].remove(s); // TreeSet만 쓰면 remove가 O(N)이다
    if (ts[group].isEmpty()) return 0;
    return ts[group].first().id;
  }

  public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {
    int[] groups = getSelectedGroup(mGradeCnt, mGrade, mGenderCnt, mGender);
    Student minStudent = null;
    for (int i = 0; i < GroupCnt; i++) {
      if (groups[i] == 0) continue;
      Student temp = ts[i].ceiling(new Student(0, mScore, i));
      if (temp == null) continue;
      if (minStudent == null || minStudent.compareTo(temp) > 0) {
        minStudent = temp;
      }
    }
    if (minStudent == null) return 0;
    return minStudent.id;
  }

  private int[] getSelectedGroup(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][]) {
    int[] selected_groups = new int[GroupCnt];
    for (int i = 0; i < mGradeCnt; i++) {
      for (int j = 0; j < mGenderCnt; j++) {
        selected_groups[toGroup(mGrade[i], mGender[j])] = 1;
      }
    }
    return selected_groups;
  }

  private int toGroup(int mGrade, char[] mGender) {
    int result = (mGrade - 1) * 2;
    if (mGender[0] == 'f') result++;
    return result;
  }


  private void printTs() {
    for (int i = 0; i < GroupCnt; i++) {
      System.out.println(Arrays.deepToString(ts[i].toArray()));

    }
  }

  private class Student implements Comparable<Student> {
    int id;
    int score;
    int group;

    public Student(int id, int score, int group) {
      this.id = id;
      this.score = score;
      this.group = group;
    }

    @Override
    public int compareTo(Student o) {
      if (score == o.score) {
        return id - o.id;
      }
      return score - o.score;
    }

    @Override
    public String toString() {
      return "Student [id=" + id + ", score=" + score + "]";
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      Student other = (Student) obj;
      return id == other.id;
    }

  }
}


class SWEA_pro_성적조회 {
  private final static int CMD_INIT = 100;
  private final static int CMD_ADD = 200;
  private final static int CMD_REMOVE = 300;
  private final static int CMD_QUERY = 400;

  private final static UserSolution_성적조회 usersolution = new UserSolution_성적조회();

  private static void String2Char(char[] buf, String str) {
    for (int k = 0; k < str.length(); ++k) buf[k] = str.charAt(k);
    buf[str.length()] = '\0';
  }

  private static boolean run(BufferedReader br) throws Exception {
    int q = Integer.parseInt(br.readLine());

    int id, grade, score;
    int cmd, ans, ret;
    boolean okay = false;

    for (int i = 0; i < q; ++i) {
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
      cmd = Integer.parseInt(st.nextToken());
      switch (cmd) {
        case CMD_INIT:
          usersolution.init();
          okay = true;
          break;
        case CMD_ADD:
          char[] gender = new char[7];
          id = Integer.parseInt(st.nextToken());
          grade = Integer.parseInt(st.nextToken());
          String2Char(gender, st.nextToken());
          score = Integer.parseInt(st.nextToken());
          ans = Integer.parseInt(st.nextToken());
          ret = usersolution.add(id, grade, gender, score);
          if (ret != ans) {
            System.out.println("add 틀렸습니다. ans : " + ans + ", ret : " + ret);
            okay = false;
          } else {
            System.out.println("add 정답입니다. ans : " + ans + ", ret : " + ret);
          }
          break;
        case CMD_REMOVE:
          id = Integer.parseInt(st.nextToken());
          ans = Integer.parseInt(st.nextToken());
          ret = usersolution.remove(id);
          if (ret != ans) {
            System.out.println("remove 틀렸습니다. ans : " + ans + ", ret : " + ret);
            okay = false;
          } else {
            System.out.println("remove 정답입니다. ans : " + ans + ", ret : " + ret);
          }
          break;
        case CMD_QUERY:
          int gradeCnt, genderCnt;
          int[] gradeArr = new int[3];
          char[][] genderArr = new char[2][7];
          gradeCnt = Integer.parseInt(st.nextToken());
          for (int j = 0; j < gradeCnt; ++j) {
            gradeArr[j] = Integer.parseInt(st.nextToken());
          }
          genderCnt = Integer.parseInt(st.nextToken());
          for (int j = 0; j < genderCnt; ++j) {
            String2Char(genderArr[j], st.nextToken());
          }
          score = Integer.parseInt(st.nextToken());
          ans = Integer.parseInt(st.nextToken());
          ret = usersolution.query(gradeCnt, gradeArr, genderCnt, genderArr, score);
          if (ret != ans) {
            System.out.println("query 틀렸습니다. ans : " + ans + ", ret : " + ret);
            okay = false;
          } else {
            System.out.println("query 정답입니다. ans : " + ans + ", ret : " + ret);
          }
          break;
        default:
          okay = false;
          break;
      }
    }
    return okay;
  }

  public static void main(String[] args) throws Exception {
    int TC, MARK;

    System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    TC = Integer.parseInt(st.nextToken());
    MARK = Integer.parseInt(st.nextToken());

    for (int testcase = 1; testcase <= TC; ++testcase) {
      int score = run(br) ? MARK : 0;
      System.out.println("#" + testcase + " " + score);
    }

    br.close();
  }
}
