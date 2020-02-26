from ArvoreRedBlack import ArvoreRedBlack

def main():
    n = 10

    bs = ArvoreRedBlack(n)
    print("ARVORE RED BLACK")
    print(bs.toString())
    print("INSERT\n----")
    key = 12
    bs.insert(key)
    key = 5
    bs.insert(key)
    key = 18
    bs.insert(key)
    key = 2
    bs.insert(key)
    key = 9
    bs.insert(key)
    key = 15
    bs.insert(key)
    key = 19
    bs.insert(key)
    key = 13
    bs.insert(key)
    key = 17
    bs.insert(key)

    print("\nAFTER INSERT\n----")
    print(bs.toString())

    r = bs.getRoot()
    bs.rb_tree_delete(r)
    print("\nAFTER DELETE\n----")
    print(bs.toString())
    
main()
