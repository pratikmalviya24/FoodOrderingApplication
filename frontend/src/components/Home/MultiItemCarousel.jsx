import React from "react";
import Slider from "react-slick";
import { TopMeels } from "./TopMeel";
import Carousel from "./Carousel";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

const MultiItemCarousel = () => {
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 5,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 2000,
    arrows:false
  };

  return (
    <div>
      <Slider {...settings}>
        {TopMeels.map((item, index) => (
          <Carousel image={item.image} title={item.title} key={index} />
        ))}
      </Slider>
    </div>
  );
};

export default MultiItemCarousel;
