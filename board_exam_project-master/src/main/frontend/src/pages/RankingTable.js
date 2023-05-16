import React from "react";

function RankingTable() {
  // 우수학습자 랭킹 데이터를 가져오는 로직
  // useEffect 훅을 사용하여 컴포넌트가 마운트될 때, 우수학습자 랭킹 데이터를 가져오도록 로직을 작성합니다.

  return (
    <table className="table">
      <thead>
        <tr>
          <th>순위</th>
          <th>이름</th>
          <th>점수</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>홍길동</td>
          <td>100</td>
        </tr>
        {/* 더 많은 학습자들의 랭킹 정보를 표시하는 로직 */}
      </tbody>
    </table>
  );
}

export default RankingTable;
