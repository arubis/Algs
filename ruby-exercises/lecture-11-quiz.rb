def runtime(c, ex, n, t)
  my_t = c * ( 10 ** ex) * (n ** 2)
  puts "#{c} x 10^#{ex} x #{n}^2 = " + my_t.to_s +
    ", expected #{t}"
end

# hardcode for now
runtime(3.3, -4, 4000, 0.1)
runtime(5.0, -9, 4000, 0.1)
runtime(6.25, -9, 4000, 0.1)

runtime(3.3, -4,  8000, 0.3)
runtime(5.0, -9,  8000, 0.3)
runtime(6.25, -9, 8000, 0.3)

runtime(3.3, -4,  16000, 1.3)
runtime(5.0, -9,  16000, 1.3)
runtime(6.25, -9, 16000, 1.3)

runtime(3.3, -4,  32000, 5.1)
runtime(5.0, -9,  32000, 5.1)
runtime(6.25, -9, 32000, 5.1)

runtime(3.3, -4,  64000, 20.5)
runtime(5.0, -9,  64000, 20.5)
runtime(6.25, -9, 64000, 20.5)
