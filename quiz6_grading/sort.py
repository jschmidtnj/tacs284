class sort:
  def __init__(self, array):
    self.array = array

  def swap(self, i, j):
    tmp = self.array[i]
    self.array[i] = self.array[j]
    self.array[j] = tmp

  def selection_sort(self):
    print("selection sort")
    print("step 0: {}".format(self.array))
    for i in range(len(self.array)):
      min_index = i
      for j in range(i + 1, len(self.array)):
        if self.array[j] < self.array[min_index]:
          min_index = j
      self.swap(i, min_index)
      print("step {}: {}".format(i + 1, self.array))

  def bubble_sort(self):
    print("bubble sort")
    print("step 0: {}".format(self.array))
    for i in range(len(self.array)):
      for j in range(0, len(self.array) - i - 1):
        if self.array[j] > self.array[j + 1]:
          self.swap(j + 1, j)
      print("step {}: {}".format(i + 1, self.array))

  def insertion_sort(self):
    print("insertion sort")
    print("step 0: {}".format(self.array))
    for i in range(1, len(self.array)):
      current = self.array[i]
      j = i - 1
      while j >= 0 and current < self.array[j]:
        self.array[j + 1] = self.array[j]
        j -= 1
      self.array[j + 1] = current
      print("step {}: {}".format(i + 1, self.array))

sort1 = sort([54, 12, 28, 17, 22, 7])
sort1.selection_sort()
sort2 = sort([54, 12, 28, 17, 22, 7])
sort2.bubble_sort()
sort3 = sort([54, 12, 28, 17, 22, 7])
sort3.insertion_sort()
