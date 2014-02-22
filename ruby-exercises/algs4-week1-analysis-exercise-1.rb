# this doesn't work.

data = [
[   131072,     0.015],
[   262144,     0.042],
[   524288,     0.114],
[  1048576,     0.315],
[  2097152,     0.867],
[  4194304,     2.387],
[  8388608,     6.580],
[ 16777216,    18.120],
[ 33554432,    49.912],
[ 67108864,   137.414],
[134217728,   378.519],
[268435456,  1042.367]]

ntimes = Array.new
data.each do |n, time|
  ntimes << time
end
ntimes.reverse!

divs = Array.new

for i in 0...(ntimes.length-1)
  divs << Math::log2( ntimes[i] / ntimes[i+1] )

end

puts divs.inspect



