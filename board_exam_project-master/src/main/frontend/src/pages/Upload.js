import React from "react";
import Preview from "../components/Preview";
import DragDrop from "../components/DragDrop";

function Upload() {
  // 우수학습자 랭킹 데이터를 가져오는 로직
  // useEffect 훅을 사용하여 컴포넌트가 마운트될 때, 우수학습자 랭킹 데이터를 가져오도록 로직을 작성합니다.

  return (
    <div>
      {/* <Preview /> */}
      <DragDrop />
    </div>
  );
}

export default Upload;
