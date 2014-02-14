# this doesn't work.

def computeB(n, time)
  b = Math::log2(time) / Math::log2(n)
end

data = [
  [8192, 0.001],
  [16384, 0.003],
  [32768, 0.008],
  [65536, 0.025],
  [131072, 0.076],
  [262144, 0.227],
  [524288, 0.685],
  [1048576, 2.043],
  [2097152, 6.137],
  [4194304, 18.358],
  [8388608, 54.985],
  [16777216, 164.938],
  [33554432, 494.309],
  [67108864, 1481.162]]

def getPoints(source)
  points = Array.new
  source.each do |time, n|
    points << computeB(time, n)
  end
  points
end

puts getPoints(data).inspect

delta = Array.new

getPoints(data).each_with_index do |point, index|
  delta[index] = point
  delta[index - 1] -= delta[index]
  delta
end

puts delta.inspect