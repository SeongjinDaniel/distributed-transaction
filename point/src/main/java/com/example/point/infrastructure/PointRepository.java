package com.example.point.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.point.domain.Point;

public interface PointRepository extends JpaRepository<Point, Long> {
  Point findByUserId(Long userId);
}
