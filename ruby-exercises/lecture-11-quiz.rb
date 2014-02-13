def runtime(c, ex, n, t)
  my_t = c * ( 10 ** ex) * (n ** 2)
  puts "#{c} x 10^#{ex} x #{n}^2 = " + my_t.to_string +
    ", expected #{t}"
end

# hardcode for now
runtime(3.3, -4, 4000, 0.1)
runtime(5.0, -9, 4000, 0.1)