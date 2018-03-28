'''
https://www.hackerrank.com/contests/image-analysis-1/challenges/digital-camera-day-or-night
'''

from sys import stdin as _in


def luminance(r, g, b):
    return 0.2126 * r + 0.7152 * g + 0.0722 * b

def solver():
    try:
        luminances = [0] * 256
        rgb = [0] * 3
        pixels = 0
        while True:
            line = _in.readline()
            if line == '' or line == '\n':
                break
            line = [x for x in line.split(" ")]
            '''
            _set_list = [x.split(",") for x in line]
            for idx in range(0, len(_set_list)):
                for i in range(0, len(_set_list[idx])):
                    _set_list[idx][i] = int(_set_list[idx][i])
            image.append(_set_list)
            '''
            for _set in line:
                r, g, b = [int(c) for c in _set.split(",")]
                gray = round(luminance(int(r), int(g), int(b)))
                luminances[gray] += 1
                rgb[0] += r
                rgb[1] += g
                rgb[2] += b
                pixels += 1
    except EOFError:
        pass
    # comeca o processamento

    acc = sum(luminances)
    rgb[0] = (rgb[0] / pixels) / 255.0
    rgb[1] = (rgb[1] / pixels) / 255.0
    rgb[2] = (rgb[2] / pixels) / 255.0
    print(acc, rgb)


solver()

if __name__ == '__main__':
    pass
