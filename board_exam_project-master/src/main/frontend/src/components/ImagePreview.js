import React from "react";

interface ImagePreviewProps {
  image: string;
  deleteFunc: () => void;
}

const ImagePreview: React.FC<ImagePreviewProps> = ({ image, deleteFunc }) => {
  return (
    <div className="ImagePreview" draggable>
      <img src={image} alt="preview" />
      <div className="icon_container" onClick={deleteFunc}>
        <i className="fas fa-times"></i>
      </div>
    </div>
  );
};

export default ImagePreview;